package com.barkovsky.check_runner.runner;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.model.error.ValidationError;
import com.barkovsky.check_runner.exception.CommandLineArgumentException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.parser.CommandLineParser;
import com.barkovsky.check_runner.parser.FileHandler;
import com.barkovsky.check_runner.repository.memory_repository.util.MemoryDbUtil;
import com.barkovsky.check_runner.service.FileOrderDecoratorService;
import com.barkovsky.check_runner.service.MemoryDiscountCardService;
import com.barkovsky.check_runner.service.MemoryProductService;
import com.barkovsky.check_runner.service.OrderService;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import com.barkovsky.check_runner.service.api.IOrderService;
import com.barkovsky.check_runner.service.api.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckRunner {
    private static final Logger logger = LoggerFactory.getLogger(CheckRunner.class);

    public static void main(String[] args) {
        IProductService productService = MemoryProductService.getInstance();
        IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();

        CommandLineParser commandLineParser = new CommandLineParser();
        IOrderService orderService = new FileOrderDecoratorService(new OrderService());

        DiscountCard discountCard = null;

        List<ValidationError> validationErrors = new ArrayList<>();

        String productFileName = null;
        String discountFileName = null;

        int counter = 0;
        for (String arg : args) {
            if (!arg.contains("-")) {
                validationErrors.add(new ValidationError(arg));
            }

            if (arg.toLowerCase(Locale.ROOT).contains("product_file") && productFileName == null) {
                productFileName = commandLineParser.getFileName(arg);
                counter++;
                continue;
            }

            if (arg.toLowerCase(Locale.ROOT).contains("discount_file") && discountFileName == null) {
                discountFileName = commandLineParser.getFileName(arg);
                counter++;
                continue;
            }

            if (counter == 2) {
                FileHandler fileHandler = new FileHandler();
                List<Product> products = fileHandler.readProduct(productFileName);
                List<DiscountCard> discountCards = fileHandler.readCard(discountFileName);
                products.forEach(productService::add);
                discountCards.forEach(discountCardService::add);
                counter = Integer.MAX_VALUE;
            } else if (counter < 2) {
                MemoryDbUtil.init(productService, discountCardService);
                counter = Integer.MAX_VALUE;
            }

            if (arg.toLowerCase(Locale.ROOT).contains("card") && discountCard == null) {
                int discountCardId = 0;
                try {
                    discountCardId = commandLineParser.getDiscountCardNumber(arg);
                } catch (CommandLineArgumentException exception) {
                    validationErrors.add(new ValidationError(arg));
                }

                try {
                    discountCard = discountCardService.get(discountCardId);
                } catch (EssenceNotFoundException e) {
                    logger.error(e.getMessage());
                    System.err.println(e.getMessage());
                    System.exit(1);
                }

                orderService.addDiscountCard(discountCard);
                continue;
            }

            int id = 0;
            int count = 0;

            try {
                id = commandLineParser.getId(arg);
                count = commandLineParser.getCount(arg);
            } catch (CommandLineArgumentException exception) {
                validationErrors.add(new ValidationError(arg));
            }

            Product product = null;
            try {
                product = productService.get(id);
            } catch (EssenceNotFoundException e) {
                logger.info(e.getMessage());
                System.err.println(e.getMessage());
                System.exit(1);
            }
            orderService.add(product, count);
        }

        if (orderService.getProducts().isEmpty()) {
            validationErrors.add(new ValidationError("Empty orderService"));
        }

        if (validationErrors.isEmpty()) {
            System.out.println(((orderService.getReceipt())));
        } else {
            System.err.println("Errors:");
            validationErrors.forEach(validationError -> System.err.println(validationError.getArgument()));
        }

    }

}
