# Catalog Management

This is the Catalog Management project built with Spring Boot. It provides APIs to manage catalogs, products, and variants.

## Features

- Manage catalogs
    - Create, read, update, and delete catalogs
- Manage products
    - Create, read, update, and delete products
- Manage variants
    - Create, read, update, and delete variants

## Technologies Used

- Spring Boot
- Java
- Maven


### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Sarthak170803/Catalog-Management.git
2. cd Catalog-Management
3. git checkout master
4. mvn clean install
5. mvn spring-boot:run

### API Endpoints
-Catalog API
1. GET /api/catalogs: Get all catalogs
2. GET /api/catalogs/{id}: Get catalog by ID
3. POST /api/catalogs: Create a new catalog
4. PUT /api/catalogs/{id}: Update a catalog by ID
5. DELETE /api/catalogs/{id}: Delete a catalog by ID
-Product API
1. GET /api/products: Get all products
2. GET /api/products/{id}: Get product by ID
3. POST /api/products: Create a new product
4. PUT /api/products/{id}: Update a product by ID
5. DELETE /api/products/{id}: Delete a product by ID
-Variant API
1. GET /api/variants: Get all variants
2. GET /api/variants/{id}: Get variant by ID
3. POST /api/variants: Create a new variant
4. PUT /api/variants/{id}: Update a variant by ID
5. DELETE /api/variants/{id}: Delete a variant by ID

### JSON Examples
- JSON for Catalogs
{
    "name": "Footwares",
    "description": "Stylish and comfortable"
}
-JSON for Products
{
    "name": "New Sneakers",
    "description": "Stylish and comfortable new sneakers",
    "basePrice": 59.99,
    "catalog": {
        "id": 1
    },
    "variants": [
        {
            "name": "Red Sneakers",
            "description": "Red colored sneakers",
            "price": 59.99
        },
        {
            "name": "Blue Sneakers",
            "description": "Blue colored sneakers",
            "price": 59.99
        }
    ]
}
-JSON for Variants
{
    "name": "Green Sneakers",
    "description": "Green colored sneakers",
    "price": 59.99,
    "product": {
        "id": 1
    }
}
