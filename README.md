# springboot-bank-demo
## API-REST

| Description                  | URL                             | Type   | Input            | Security      |
| ---------------------------- | ------------------------------- | ------ | ---------------- | ------------- | 
| Login                        | /auth/login                     | POST   | LoginForm        | Any user      |
| Logout                       | /auth/logout                    | GET    |                  | Any User      |
| Add new account              | /account                        | POST   | AccountDTO       | Any User      |
| Delete account               | /account/{accountID}            | DELETE |                  | Any User      |
| Get all accounts by customer | /account/customer/{customerID}  | GET    |                  | Any User      |
| Add new customer             | /customer                       | POST   | CustomerDTO      | Any User      |
| Delete customer              | /customer/{customerID}          | DELETE |                  | Any User      |
| Deposit money                | /bank/deposit                   | POST   | DepositForm      | LoggedIn User |
| Withdraw money               | /bank/withdraw                  | POST   | WithdrawallForm  | LoggedIn User |
| Get account state            | /bank/state/{accountID}         | GET    |                  | LoggedIn User |

## Forms
### LoginForm
```json
{
	"mail": "customer1@gmail.com",
	"password": "admin"
}
```
### Default created login user
```json
{
	"mail": "leandrofiaschetti@gmail.com",
	"password": "admin"
}
```

### CustomerDTO
```json
{
    "lastName":"LastNameCustomer2",
    "firstName":"FirstNameCustomer2",
    "mail":"cliente2@gmail.com",
    "password":"customer2"
}
```
### AccountDTO
```json
{
	"type": "S",
	"balance":0,
	"ownerId":1
}
```
### DepositForm
```json
{
	"accountId": 2,
	"amount": 10
}
```
### WithdrawallForm
```json
{
	"accountId": 2,
	"amount": 10
}
```
