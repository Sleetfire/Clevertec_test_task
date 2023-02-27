# Задания по Reflection API и String/JSON

## Описание

Задания находятся в пакете reflection_json

### Описание Reflection API

Задание по Reflection API находится в пакете reflection_task. 
Чтение параметров для емкости кэша и тип алгоритма происходит из application.yml. Для этого необходимо указать параметры:
- cash-algorithm: или LFU, или LRU.
- cash-capacity: положительное число, например, 3. 

Добавление в кэш происходит за счет Dynamic Proxy (имплементация InvocationHandler).

Для проверки правильности работы алгоритмов LFU и LRU  и сервиса были написаны тесты, которые находятся в пакете reflection_json.reflection_task.

### Описание String/JSON

Задание по String/JSON находится в пакете json_mapper.

Класс JsonMapper позволяет записать Java-объект в Json-строку и прочитать из Json-строки в Java-объект.

Для проверки правильности работы был написан тест, который находится в пакете reflection_json.json_mapper.
