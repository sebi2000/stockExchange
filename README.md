# stockExchange
Fuctional req
 - client can login/logout in the app 
 - client can see a table with buy offers and sell offers
 - client can list a new offer/ demand (buy or sell)
 - client can see offer history
 - client can edit his own buy or sell offers
 - market decides when to match sellers and buyers (when an offer has the same price/share with a demand, nr_of_shares = min(offer, demand)) 
 - market adjust the amount of stocks available after a offer
 - market updates the wallet of a user after a offer (either add/subtract money/stocks)
 - market keeps track of offers history
 
Non-functional req
  - reliability: if the server crashes, the offer will be saved in an intermediate file
  - performance: all requests should be done in 1 second, even when the number of concurrent users in the app increases
  
Architectural Diagram
![image](https://user-images.githubusercontent.com/30629359/198845221-9ad4d2e9-5e36-4785-b657-43d0171ad7e5.png)


 
