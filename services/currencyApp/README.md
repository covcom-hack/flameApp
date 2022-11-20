# Сервис по обновлению и предоставлению курсов валют
How to use:

```
    git clone <url>
    docker build -t "<name>" .
    docker run -p 8080:8080 <name>
```

# REST API
### `GET /api/v1/currency`
### Request
    {
        "name": "RUB"
    }

### Response
    {
        "statusCode": 200,
        "method": "GET",
        "message": "Success",
        "data": {
            "name": "RUB",
            "currency": 1,
            "time": "2022-11-20T11:32:34.168726045+03:00"
        }
    }

### `GET /api/v1/currency/all`
### Request
    {
        "name": "USD"
    }

### Response
    {
        "statusCode": 200,
        "method": "GET",
        "message": "Success",
        "data": {
            "base": "USD",
            "currencies": [
                {
                    "name": "CNY",
                    "amount": 0.1420525899754842
                },
                {
                    "name": "DKK",
                    "amount": 0.13868442328743796
                },
                {
                    "name": "TRY",
                    "amount": 0.0544607742059042
                },
                {
                    "name": "RUB",
                    "amount": 0.016612678374310897
                },
                {
                    "name": "AZN",
                    "amount": 0.5906876980673053
                },
                {
                    "name": "HKD",
                    "amount": 0.12848495988790212
                },
                {
                    "name": "SEK",
                    "amount": 0.09432248031976966
                },
                {
                    "name": "UAH",
                    "amount": 0.02727306890504946
                },
                {
                    "name": "CAD",
                    "amount": 0.7527984658825104
                },
                {
                    "name": "GBP",
                    "amount": 1.1838995440871345
                },
                {
                    "name": "KRW",
                    "amount": 0.0007422012590376533
                },
                {
                    "name": "SGD",
                    "amount": 0.736026144582335
                },
                {
                    "name": "RON",
                    "amount": 0.2132874397470831
                },
                {
                    "name": "EUR",
                    "amount": 1.0341361005855805
                },
                {
                    "name": "CHF",
                    "amount": 1.0528624380868177
                },
                {
                    "name": "HUF",
                    "amount": 0.0025268542941891824
                },
                {
                    "name": "ZAR",
                    "amount": 0.05712491069376431
                },
                {
                    "name": "NOK",
                    "amount": 0.09961386878949019
                },
                {
                    "name": "UZS",
                    "amount": 0.00008916761245784657
                },
                {
                    "name": "TMT",
                    "amount": 0.28957404753634947
                },
                {
                    "name": "USD",
                    "amount": 1
                },
                {
                    "name": "AMD",
                    "amount": 0.0025184105198297006
                },
                {
                    "name": "BRL",
                    "amount": 0.18553424289283213
                },
                {
                    "name": "JPY",
                    "amount": 0.007197074526359147
                },
                {
                    "name": "KGS",
                    "amount": 0.011873337616139032
                },
                {
                    "name": "AUD",
                    "amount": 0.6677497485005801
                },
                {
                    "name": "BYN",
                    "amount": 0.4203341080529394
                },
                {
                    "name": "PLN",
                    "amount": 0.22254000890363082
                },
                {
                    "name": "KZT",
                    "amount": 0.0021747019308235413
                },
                {
                    "name": "INR",
                    "amount": 0.012330059902063175
                },
                {
                    "name": "CZK",
                    "amount": 0.0430959133000829
                },
                {
                    "name": "BGN",
                    "amount": 0.5278979920186062
                },
                {
                    "name": "TJS",
                    "amount": 0.09926557522991543
                },
                {
                    "name": "XDR",
                    "amount": 1.3050133339139838
                },
                {
                    "name": "MDL",
                    "amount": 0.052968726745816166
                }
            ],
            "time": "2022-11-20T13:33:00.112169764+03:00"
        }
    }
