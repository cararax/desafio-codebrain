### Code Brain💻🧠 - Backend Challenge

<h1 align="center">
Sales Score System
</h1>

<p align="center">
  <a href="#about-the-project">About the project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#how-to-run">How to run de project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#example-requests">Examples of API requests</a>&nbsp;&nbsp;&nbsp;
</p>

## 🎯 About the project

<a id="about-the-project"></a>

Sales Score System is an API responsible for managing a company's products, sellers and sales. In the system you can:

- Create, update and delete products;
- Create, update, delete e get sellers by id;
- Create sales, as well as consult the sellers with the highest number of sales and consult the average sales ticket of
  a seller by time interval.

## 🚀 Technologies used

<a id="technologies"></a>

- Java
- Maven
- Spring Boot
- Spring Data JPA
- Validation
- Lombok
- JUnit
- Mockito
- Swagger
- PostgreSQL
- Docker

## 💻 How to run de project

<a id="how-to-run"></a>
### On Docker containers

1. Clone the project and access the repository folder:

```bash
git clone https://github.com/cararax/desafio-codebrain && cd desafio-codebrain
```

2. Build and run the application with docker-compose:

```bash
docker-compose up
```

This command will build and run the application and database container. The application will automatically create the
tables and populate the database.

The application will be running at http://localhost:8080/

Check the documentation at http://localhost:8080/api/swagger-ui/index.html

---

### Run locally

1. Clone the project and access the repository folder:

```bash
git clone https://github.com/cararax/desafio-codebrain && cd desafio-codebrain
```

2. Create a container for the database:

```bash
docker run --name salesScoreService -e POSTGRES_PASSWORD=password -e POSTGRES_DB=salesScoreService -p 5432:5432 -d postgres:alpine
```
This command will build and run the database on a docker container

3. Set application profile to dev:

Change the application.properties file to:

```properties
spring.profiles.active=dev
```

4. Run the application

On SalesScoreSystem folder run the command:
```bash
mvn spring-boot:run
```

This command will run the application locally with a database container. The application will automatically create the tables and populate the database.

The application will be running at http://localhost:8080/

Check the documentation at http://localhost:8080/api/swagger-ui/index.html

---

## 📄 Examples of API requests

<a id="example-requests"></a>

### Products

| Method | Url                     | Description           | Sample requests           |
|--------|-------------------------|-----------------------|---------------------------|
| POST   | /api/products           | Create a product | [Request](#create-product) |
| PUT    | /api/products/{productId} | Update a product         | [Request](#update-product) |
| DELETE | /api/products/{productId}        | Delete a product         |     |

### Sellers

| Method | Url               | Description                                                                | Sample requests |
|--------|-------------------|----------------------------------------------------------------------------|---------------------------|
| GET    | /api/sellers/{sellerId} | Get seller by id                                                           |                           |
| POST   | /api/sellers      | Create seller                                                              | [Request](#create-seller) |
| PUT    | /api/sellers/{sellerId} | Update seller                                                              | [Request](#update-seller) |
| DELETE | /api/sellers/{sellerId} | Delete seller  |                           |

### Sales

| Method | Uri                                | Description                                           | Sample requests     |
|--------|------------------------------------|-------------------------------------------------------|--------------------------|
| GET    | /api/sales/sales-by-seller         | Get list of sellers with the highest number of sales  |                          |
| GET    | /api/sales/sales-ticket/{sellerId} | Get average sales ticket of a seller by time interval | [Request](#sales-ticket) |
| POST   | /api/sellers                       | Create sale                                           | [Request](#create-sale)  |

### Sample requests

### Products

<a id="create-product"></a>**Create a product**

**POST** -> localhost:8080/api/products

```json
{
  "name": "Product Name",
  "price": 12.34
}
```

<a id="update-product">#</a>**Update a product**

**PUT** -> /api/products/{productId}

```json
{
  "name": "Product Updated",
  "price": 43.21
}
```

### Sellers

<a id="create-seller"></a>**Create a seller**

**POST** -> localhost:8080/api/sellers

```json
{
  "name": "Seller Name"
}
```

<a id="update-seller"></a>**Update a seller**

**PUT** -> /api/products/{productId}

```json
{
  "name": "Seller Updated"
}
```

### Sales

<a id="sales-ticket"></a>**Get a sales ticket from a seller**

**GET** -> localhost:8080/api/sales/sales-ticket/{sellerId}

```json
{
  "startDate": "2022-02-01",
  "endDate": "2022-04-28"
}
```

<a id="create-sale"></a>**Create a sale**

**POST** -> localhost:8080/api/sales

```json
{
  "sellerId": 11,
  "productId": [
    1,
    2,
    3,
    4,
    5
  ]
}
```

<br><br>
---
Made with ☕ and 💖 by **Pedro Carara**
