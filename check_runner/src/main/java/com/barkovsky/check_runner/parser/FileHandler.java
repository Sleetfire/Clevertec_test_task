package com.barkovsky.check_runner.parser;

import com.barkovsky.check_runner.exception.DeserializationException;
import com.barkovsky.check_runner.exception.SerializationException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<Product> readProduct(String fileName) {
        List<Product> products;
        try {
            Path file = Path.of(fileName);
            String content = Files.readString(file);
            products = List.of(this.objectMapper.readValue(content, Product[].class));
        } catch (IOException e) {
            throw new DeserializationException();
        }
        return products;
    }
    public List<DiscountCard> readCard(String fileName) {
        List<DiscountCard> discountCards;
        try {
            Path file = Path.of(fileName);
            String content = Files.readString(file);
            discountCards = List.of(this.objectMapper.readValue(content, DiscountCard[].class));
        } catch (IOException e) {
            throw new DeserializationException();
        }
        return discountCards;
    }

    public <T> void write(String fileName, List<T> info) {
        try  {
          this.objectMapper.writeValue(new File(fileName), info);
        } catch (IOException e) {
            throw new SerializationException();
        }
    }

}
