# Product App

### Version
V1

### How to run?
   1) git clone https://github.com/syedfaisal3/profanity_validator_app.git
   2) cd profanity_validator_app
   3) mvn clean install
   4) mvn spring-boot:run -pl api

### sample request and response for positive scenario

    curl -H "Content-Type:application/json" -X POST  --data '{"reviewText":"great product "}' http://localhost:8080/product/v1/1229/review.json

    (here 1229 is the product id)

    {
      "success": true,
      "errors": [

      ],
      "result": [
        "createReviewSuccess"
      ]
    }

### sample request and response for negative scenario


    curl -H "Content-Type:application/json" -X POST  --data '{"reviewText":"what a shitty product"}' http://localhost:8080/product/v1/1229/review.json

    {
        "success": false,
        "errors": [
            {
                "message": "textContainsProfanity",
                "code": "1001"
            },
            {
                "message": "what a ****** product",
                "code": "4001"
            }
        ]
    }

    curl -H "Content-Type:application/json" -X POST  --data '{"reviewText":"@ss"}' http://localhost:8080/product/v1/1229/review.json

    {
        "success": false,
        "errors": [
            {
                "message": "textContainsProfanity",
                "code": "1001"
            },
            {
                "message": "***",
                "code": "4001"
            }
        ]
    }
