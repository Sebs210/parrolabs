Para nuestro sistema de ventas, podríamos tener los siguientes servicios:

## CustomerService
* saveCustomer(Customer customer): guarda un nuevo cliente en la base de datos.
* updateCustomer(Customer customer): actualiza los datos de un cliente existente en la base de datos.
* deleteCustomer(Long id): elimina un cliente existente en la base de datos.
* getCustomerById(Long id): obtiene un cliente existente en la base de datos por su ID.
* getAllCustomers(): obtiene todos los clientes existentes en la base de datos.
* getCustomerByEmail(String email): obtiene un cliente existente en la base de datos por su dirección de correo electrónico.
* getCustomerByPhone(String phone): obtiene un cliente existente en la base de datos por su número de teléfono.

## ProductService
* saveProduct(Product product): guarda un nuevo producto en la base de datos.
* updateProduct(Product product): actualiza los datos de un producto existente en la base de datos.
* deleteProduct(Long id): elimina un producto existente en la base de datos.
* getProductById(Long id): obtiene un producto existente en la base de datos por su ID.
* getAllProducts(): obtiene todos los productos existentes en la base de datos.

## OrderService
* saveOrder(Order order): guarda un nuevo pedido en la base de datos.
* updateOrder(Order order): actualiza los datos de un pedido existente en la base de datos.
* deleteOrder(Long id): elimina un pedido existente en la base de datos.
* getOrderById(Long id): obtiene un pedido existente en la base de datos por su ID.
* getAllOrders(): obtiene todos los pedidos existentes en la base de datos.
* calculateOrderTotal(Order order): calcula el valor total de un pedido en función de sus productos.
##PaymentTypeService
* savePaymentType(PaymentType paymentType): guarda un nuevo tipo de pago en la base de datos.
* updatePaymentType(PaymentType paymentType): actualiza los datos de un tipo de pago existente en la base de datos.
* deletePaymentType(Long id): elimina un tipo de pago existente en la base de datos.
* getPaymentTypeById(Long id): obtiene un tipo de pago existente en la base de datos por su ID.
* getAllPaymentTypes(): obtiene todos los tipos de pago existentes en la base de datos.


Cada uno de estos servicios podría tener una implementación que haga uso de los repositorios para interactuar con la base de datos. Por ejemplo, el CustomerService podría tener una implementación que haga uso del CustomerRepository para realizar las operaciones de CRUD en la base de datos. Además, estos servicios podrían tener métodos adicionales que utilicen la lógica de negocio para realizar otras tareas específicas de la aplicación.


# La clase OrderController define cinco puntos finales:

* POST /orders crea un nuevo pedido con la información proporcionada en el cuerpo de la solicitud.
* PUT /orders/{orderId} actualiza un pedido existente con la información proporcionada en el cuerpo de la solicitud.
* DELETE /orders/{orderId} cancela un pedido existente.
* GET /pedidos/{orderId} recupera los detalles de un pedido específico.
* GET /orders recupera una lista de todos los pedidos.
