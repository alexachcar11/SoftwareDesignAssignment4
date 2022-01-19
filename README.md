# SoftwareDesignAssignment4
Assignment features multiple design patterns and contains some white and black box testing

For this assignment, I wrote a new class to conduct operations for enhanced watchlists. I used the composite design pattern in order to group together WatchListFilters which in turn allowed me to organize them into either a conjunction or a disjunction. Even though fundamentally each of the three filters I initialized are different from one another, they all implemented WatchListFilter, which allowed me to compose them together. By implementing my code this way, my library became very easily scalable if new filters are to be added. The person working on the code would have to add new filter strategy interfaces and their respective overridden methods.

Further, within the episode class, I overrided the clone() method, which took all of the fields from the prototype, besides the episode number, which I assigned to the total number of episodes in the show plus one. This allows the client to continue to clone the same episode if they wish. I added a setPrototype() and createEpisode() method in the TVShow class which controlled the use of the prototype. 
