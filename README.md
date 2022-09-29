## bank-app
This is a simple web application implemented by the Spring Boot modular framework and simulates a bank system. In general, the application has a three-level architecture:
- Repositories layer: for works with database
- Controllers layer: for handling requests and responses
- Service layer: include business logic

The basis of the application is three models: Account, Bank, and Transaction.

### User capabilities
The application supports http requests, with which you can:
1. Create an Account: `[POST] localhost:8080/account/save` ➡️ JSON: `{"userName":"Dima", "amount":100}`
2. Get an Account by ID: `[GET] localhost:8080/account/{accountId}`
3. Create a Bank: `[POST] localhost:8080/bank/save` ➡️ JSON: `{"nameBank":"Mono", "totalTransactionFeeAmount":0, "totalTransferAmount":0, "flatFee":10, "percentFee":5}`
4. Get the total commission received by the Bank: `[GET] localhost:8080/bank/{bankId}/get-total-fee-amount`
5. Get the total amount of funds transferred through the Bank: `[GET] localhost:8080/bank/{bankId}/get-total-transfer-amount`
6. Add a Bank Account: `[PUT] localhost:8080/bank/{bankId}/add-account?accountId=1`
7. Get a list of all Accounts available in the Bank (only after completing item 6): `[GET] localhost:8080/bank/{bankId}/get-accounts`
8. Make a Transaction between Accounts with a flat commission of a specific Bank: `[POST] localhost:8080/transaction/flat-fee/bank/{bankId}` ➡️ JSON: `{"amount":50, "fromAccountId":1, "toAccountId":2, "transactionReason":"I have a lot of money)"}`
9. Make a Transaction between Accounts with a percent commission of a specific Bank: `[POST] localhost:8080/transaction/percent-fee/bank/{bankId}` + JSON
10. Deposit the Account without commission: `[POST] localhost:8080/transaction/deposit` ➡️ JSON: `{"amount":50, "toAccountId":2, "transactionReason":"Let's deposit money"}`
11. Withdraw funds from the Account without commission: `[POST] localhost:8080/transaction/withdrawal` ➡️ JSON: `{"amount":50, "fromAccountId":2, "transactionReason":"Let's do it"}`
12. View the history of all Transactions of a specific Account: `[GET] localhost:8080/transaction/history-account/{accountId}`

### Project launch:
- Clone this project
- The project is ready for launch
- For your convenience, you can send a request `localhost:8080/inject` дto initialize two Accounts and one Bank
- Use a request `localhost:8080/h2-console` to view the data in the database H2

