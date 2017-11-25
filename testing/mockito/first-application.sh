#/bin/bash

javac Portfolio.java PortfolioTester.java Stock.java StockService.java -cp mockito.jar:.
java -cp .:mockito.jar PortfolioTester
