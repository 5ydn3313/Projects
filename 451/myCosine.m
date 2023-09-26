% myCosine
% Takes in x values and outputs a Taylor series expansion 
%
% Usage:
%           cosx = myCosine[x]
%           
% Where:
%           input_args  = user iputed x values which is equal to the domain
%           of the cosine curve 
%           output_args = y values/ range of cosine graph 
%
% 
% Created By: 
%           Sydney Pennington
%           09/20/22
%

function y = myCosine(x)
% myCosine function
%Sydney Pennington 


% First map all the x values into the the range from 0 to 2*pi
x = abs(rem(x,2*pi));

% First map all the x values into the the range from 0 to 2*pi
x = abs(rem(x,2*pi));

% use this vector to keep track of whether you need a minus sign (start with +1)
matrix = ones(size(x));

% Now handle the four cases


% case 1
indices = find(((x >= 0) & (x <= pi/2)));
xx(indices) = x(indices);
matrix(indices) = 1;

% case 2
indices = find(((x > pi/2) & (x <= pi)));
xx(indices) = pi - x(indices);
matrix(indices) = -1;

% case 3
indices = find(((x > pi) & (x <= (1.5)*pi)));
xx(indices) = x(indices) - pi;
matrix(indices) = -1;

% case 4
indices = find(((x > 1.5*pi)  & (x <= 2*pi)));
xx(indices) = 2*pi - x(indices);
matrix(indices) = 1;


% Now compute your Taylor series, but include the possible minus sign
% I will let you figure out how many terms you need.  Here I just have two, which is clearly not enough

y = matrix .* (1 - xx.^2./factorial(2) + xx.^4./factorial(4)- xx.^6./factorial(6) + xx.^8./factorial(8));

fprintf('Result: %.3f.\n',y);






