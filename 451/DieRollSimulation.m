%Syndey Pennington
%Part e 
clc, clearvars, format compact

r = 1000;

% 6 roll dice
results = randi(6,6,r); 
sixesCheck = results == 6; %makes each six value a 1 in each 
numSixes =sum(sixesCheck); % takes the sum of each six in each column 
sixSets= numSixes >= 1; % records the sum of all hands that included at least 1 six rolled 

probofSix = sum(sixSets) / r 


% 12 roll dice 
resultsB = randi(6,12,r); 
sixesCheckB = resultsB == 1; %makes each six value a 1 in each 
numSixesB =sum(sixesCheckB); % takes the sum of each six in each column 
sixSetsB= numSixesB >= 2; % records the sum of all hands that included at least 1 six rolled 

probofSixB = sum(sixSetsB) / r 

% 18 roll dice 
resultsC = randi(6,18,r); 
sixesCheckC = resultsC == 1; %makes each six value a 1 in each 
numSixesC =sum(sixesCheckC); % takes the sum of each six in each column 
sixSetsC= numSixesC >= 3; % records the sum of all hands that included at least 1 six rolled 

probofSixC = sum(sixSetsC) / r 



%y=pdf(name,r,6,6);
