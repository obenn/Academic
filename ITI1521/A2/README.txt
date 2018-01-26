ITI1521 Assignement 2
Oliver Benning 7798804
Feb 12 2017

Q1: Picks random numbers within a certain range until a match is found, repeats this a certain number of times.
The result of each experiment is fed into a statistics class, which provides a string of the min, max, average
and standard deviation values once completed. Default values are 50 and 365 for range and tries respectively, 
in order to answer the "birthday paradox".

Q2: Retains the same statistics logic as Q1 but now performs the experiment with varying ranges, starting from
a certain amount incremented to a maximum. At every increment, a data point is added to a custom JFreeChart
method which graphs the result at the end along with 3 similar power ratios, concluding that the square root
is a good approximation.