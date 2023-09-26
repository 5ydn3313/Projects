% a) Write a SCRIPT to verify that computers do indeed make mistakes
%    - Create a loop that adds 0.1 to a variable 10 times
%    - Display the result
%    - Does this give you the correct answer of 0.1 * 10 = 1?
%    - ARE YOU SURE ITS CORRECT?  Try subtracting 1.0 to see the difference
%    - How large is the error in the calculation?
%

%Sydney Pennington HW1

%% for loop to add 0.1 to every variable 
clc;
tic;

x=0;
for loop = 1:10
    x = x + 0.1; % incremented variable
end

disp(x);

y= (x-1.0);

disp(y);
perError = abs(y/x)* 100; 
disp(perError);

toc;
%% b) Write a SCRIPT where you vectorize the above code:
%    - Create a 1x10 vector of values equal to 0.1
%    - Use the sum function to sum them
%    - What is the error in this case?
%    - Use the functions 'tic' and 'toc' to determine whether
%      the vectorized code is faster or slower than the above code.  
%      Note that you may have to loop through the calculation many 
%      times to get a good estimate.  Make sure you get a good estimate!

vector = [0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1];
vector = sum(vector);
disp(vector); %displays vector sum 
toc;