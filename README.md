# Тестовое задание для Сбера
## Задание
Реализовать приложение, которое выполняет CRUD операции над Объектом (объект определяет сам кандидат).

## Описание проекта
В качестве объекта для CRUD операций выбран товар из продуктового магазина. У товара есть следующие 5 полей:
- id - идентификатор
- title - название
- quantity - количество
- createdAt - дата создания товара в БД
- updatedAt - дата обновления товара в БД

Название товара не может быть пустым, а количество не может быть меньше 0. Эти требования реализованы в логике приложения и проверены с помощью тестов.

## Требования к запуску приложения
На Вашей машине должен быть установлен Docker и Docker Compose.

## Запуск приложения
1. Склонировать репозиторий:
 ```console
 git clone https://github.com/TheDuke2021/sberbank-crud.git
 ```
2. Перейти в папку с проектом, ввести следующую команду:
```console
docker compose up -d
```
Подождите пару секунд, пока приложение поднимется.
База данных заполняется 500 тестовыми товарами.

## Использование
Для проверки API можно использовать `Postman` или `curl`.
<br>

### Получение всех товаров
Выполните `GET` запрос по адресу `http://localhost/items`, например с помощью `curl` в терминале:
```console
curl 'http://localhost/items'
```

Вернётся результат:
```json
[{"id":1,"title":"Tequila - Sauza Silver","quantity":217},{"id":2,"title":"Beans - Kidney White","quantity":404},{"id":3,"title":"Veal - Liver","quantity":406},{"id":4,"title":"Doilies - 7, Paper","quantity":514},{"id":5,"title":"Pepper - Chillies, Crushed","quantity":757},{"id":6,"title":"Nut - Pine Nuts, Whole","quantity":450},{"id":7,"title":"Cookie Dough - Oatmeal Rasin","quantity":731},{"id":8,"title":"Banana - Green","quantity":30},{"id":9,"title":"Turkey - Whole, Fresh","quantity":395},{"id":10,"title":"Blouse / Shirt / Sweater","quantity":141},{"id":11,"title":"Beer - Sleemans Cream Ale","quantity":429},{"id":12,"title":"Pasta - Lasagna Noodle, Frozen","quantity":458},{"id":13,"title":"Graham Cracker Mix","quantity":60},{"id":14,"title":"Apples - Spartan","quantity":76},{"id":15,"title":"Beets - Pickled","quantity":279},{"id":16,"title":"Foil Wrap","quantity":46},{"id":17,"title":"Table Cloth 62x120 Colour","quantity":504},{"id":18,"title":"Bread - White, Sliced","quantity":940},{"id":19,"title":"Vinegar - Raspberry","quantity":172},{"id":20,"title":"Nougat - Paste / Cream","quantity":546},{"id":21,"title":"Beer - Upper Canada Light","quantity":885},{"id":22,"title":"Lettuce - Treviso","quantity":301},{"id":23,"title":"Goulash Seasoning","quantity":996},{"id":24,"title":"Olives - Green, Pitted","quantity":86},{"id":25,"title":"Wine - Red, Pelee Island Merlot","quantity":895},{"id":26,"title":"Beef - Top Sirloin","quantity":704},{"id":27,"title":"Peach - Halves","quantity":14},{"id":28,"title":"Aspic - Light","quantity":335},{"id":29,"title":"Jam - Apricot","quantity":342},{"id":30,"title":"Veal - Inside, Choice","quantity":162},{"id":31,"title":"Basil - Dry, Rubbed","quantity":642},{"id":32,"title":"Nantucket - Pomegranate Pear","quantity":868},{"id":33,"title":"Ham - Cooked","quantity":790},{"id":34,"title":"Duck - Whole","quantity":160},{"id":35,"title":"Pomegranates","quantity":98},{"id":36,"title":"Flour - Rye","quantity":686},{"id":37,"title":"Syrup - Golden, Lyles","quantity":176},{"id":38,"title":"Wine - Sauvignon Blanc Oyster","quantity":773},{"id":39,"title":"Soup - Campbells Chicken","quantity":338},{"id":40,"title":"Red Currant Jelly","quantity":522},{"id":41,"title":"Apricots Fresh","quantity":148},{"id":42,"title":"Wine - Rioja Campo Viejo","quantity":91},{"id":43,"title":"Instant Coffee","quantity":707},{"id":44,"title":"Wine - Sauvignon Blanc","quantity":594},{"id":45,"title":"Beer - Upper Canada Lager","quantity":753},{"id":46,"title":"Milk - Chocolate 500ml","quantity":720},{"id":47,"title":"Veal - Inside, Choice","quantity":961},{"id":48,"title":"Rolled Oats","quantity":929},{"id":49,"title":"Sauce - White, Mix","quantity":301},{"id":50,"title":"Rice - Jasmine Sented","quantity":66}]
```

__Примечание__: вывод товаров происходит с помощью пагинации. Для контроля параметров пагинации используйте query-параметры `perPage` и `page`. Нумерация `page` начинается с 1. Если параметры пагинации не указаны - используются параметры по умолчанию (`perPage` = 50 и `page` = 1). `perPage` нельзя установить больше 50.

Например, `GET` запрос по адресу `http://localhost/items?perPage=20&page=10`:
```console
curl 'http://localhost/items?perPage=20&page=10'
```
вернёт результат:
```json
[{"id":181,"title":"Teriyaki Sauce","quantity":494},{"id":182,"title":"Cilantro / Coriander - Fresh","quantity":557},{"id":183,"title":"Nut - Macadamia","quantity":113},{"id":184,"title":"Ostrich - Prime Cut","quantity":747},{"id":185,"title":"Green Tea Refresher","quantity":969},{"id":186,"title":"Beer - Fruli","quantity":585},{"id":187,"title":"Tarragon - Fresh","quantity":577},{"id":188,"title":"Capon - Breast, Wing On","quantity":878},{"id":189,"title":"Beef - Tenderloin Tails","quantity":790},{"id":190,"title":"Bread - Pita","quantity":396},{"id":191,"title":"Coffee - Beans, Whole","quantity":222},{"id":192,"title":"The Pop Shoppe - Black Cherry","quantity":488},{"id":193,"title":"Pork - Bones","quantity":807},{"id":194,"title":"Bag - Regular Kraft 20 Lb","quantity":812},{"id":195,"title":"Parsnip","quantity":229},{"id":196,"title":"The Pop Shoppe - Lime Rickey","quantity":934},{"id":197,"title":"Lotus Root","quantity":855},{"id":198,"title":"Devonshire Cream","quantity":255},{"id":199,"title":"Soup - Cream Of Potato / Leek","quantity":845},{"id":200,"title":"Taro Root","quantity":86}]
```
<br>

### Получение товара по id
Выполните  `GET`  запрос по адресу  `http://localhost/items/15`, например с помощью  `curl`  в терминале:
```console
curl 'http://localhost/items/15'
```
Вернётся результат:
```json
{"id":15,"title":"Beets - Pickled","quantity":279}
```
<br>

### Создание товара
Выполните  `POST`  запрос по адресу  `http://localhost/items` с телом в HTTP-запросе:
```json
{"title": "Ice Cream", "quantity": 16}
```
например с помощью  `curl`  в терминале:
```console
curl -X POST -H 'Content-Type: application/json' -d '{"title": "Ice Cream", "quantity": 16}' 'http://localhost/items'
```
Вернётся результат:
```json
{"id":501,"title":"Ice Cream","quantity":16}
```
<br>

### Обновление товара
Выполните `PUT` запрос по адресу `http://localhost/items/100` с телом в HTTP-запросе:
```json
{"title": "Kebab", "quantity": 300}
```
например с помощью  `curl`  в терминале:
```console
curl -X PUT -H 'Content-Type: application/json' -d '{"title": "Kebab", "quantity": 300}' 'http://localhost/items/100'
```
Теперь выполните `GET` запрос по тому же адресу `http://localhost/items/100`:
```console
curl 'http://localhost/items/100'
```

Вернётся результат:
```json
{"id":100,"title":"Kebab","quantity":300}
```
<br>

### Удаление товара
Выполните `DELETE` запрос по адресу `http://localhost/items/100`, например с помощью  `curl`  в терминале:
```console
curl -X DELETE 'http://localhost/items/100'
```
После этого выполните `GET` запрос по тому же адресу `http://localhost/items/100`:
```console
curl 'http://localhost/items/100'
```
Вернётся результат:
```json
{"status":404,"timestamp":"2024-02-19T22:11:09.981076853","message":"Item with id 100 was not found!"}
```
<br>

### Валидация входных данных
#### Проверка на пустое название товара
Выполните  `POST`  запрос по адресу  `http://localhost/items` с телом в HTTP-запросе:
```json
{"title": "", "quantity": 32}
```
например с помощью  `curl`  в терминале:
```console
curl -X POST -H 'Content-Type: application/json' -d '{"title": "", "quantity": 32}' 'http://localhost/items'
```
Вернётся результат:
```json
{"status":400,"timestamp":"2024-02-19T22:16:08.669716942","message":"Title of an item must not be empty!"}
```
<br>

#### Проверка на отрицательное значение количества товара
Выполните  `POST`  запрос по адресу  `http://localhost/items` с телом в HTTP-запросе:
```json
{"title": "Golden Apple", "quantity": -100}
```
например с помощью  `curl`  в терминале:
```console
curl -X POST -H 'Content-Type: application/json' -d '{"title": "Golden Apple", "quantity": -100}' 'http://localhost/items'
```
Вернётся результат:
```json
{"status":400,"timestamp":"2024-02-19T22:17:00.983650629","message":"Quantity of an item must not be negative!"}
```

## Тесты
Написаны unit- и интеграционные тесты. Запустить их можно с помощью Maven:
```console
./mvnw clean test
```
или через IntelliJ IDEA.