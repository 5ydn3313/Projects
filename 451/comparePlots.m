domain = linspace(0,2*pi,100);

c = cos(x);

m = myCosine(x);

cGraph = figure(1);%COS
plot(domain, c)

myCGraph = figure(2);%myCosine
plot(domain, m)

figure(3);%compare cosx and myCosine(x)
plot(domain,cGraph); hold on;
plot(domain, myCGraph)