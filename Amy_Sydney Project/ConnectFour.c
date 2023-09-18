/*
    Amy Shamraj & Sydney Pennington
    ICSI 333
    ConnectFour.c Project 3
    11/22/2020
*/

/*
    To Compile
        - gcc -o ConnectFour ConnectFour.c
    To Run
        Server
            - ./ConnectFour PORT
            example: 
                ./ConnectFour 5000
        Client
            - ./ConnectFour Address PORT
            example: 
                ./ConnectFour 127.0.0.1 5000
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>
#include <stdbool.h>
#include <ctype.h>

// The Board
char ** board;

// These buffers will take in the player names
char p1name[50];
char p2name[50];

// These two variables will take in the player inputs
char p1input;
char p2input;

/*  
    These are the flags that will be used to control the state of the game 

    For playerTurn, 1 means it's Player 1's turn, 2 means it's Player 2's turn, -1 means that player 1 quit and -2 means that player 2 quit. Default is Player 1's turn.
    For flag, 1 means the game should keep running. 0 Should make the game end. Default is 1.
    For winner, 1 means that Player 1 wins, 2 means that Player 2 wins
*/
int playerTurn = 1;
int flag = 1;
int winner = 0;

// IP Address String
// number of arguments passed in main (used to detect server vs client mode)
char * address;
int totalArgs;

// Client and Server Network Variables
int PORT = 6000; // Default is 6000
struct sockaddr_in serverAddress;
struct sockaddr_in clientAddress;
int socketClient;
int socketServer;
int socketServerfd;


// Client Mode
void client() {
    
    // Setting up network variables for the Client
    bzero(&serverAddress, sizeof(serverAddress));
    printf("PORT: %d and IP: %s\n", PORT, address);
    serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = inet_addr(address);
    socketClient = socket(AF_INET, SOCK_STREAM, 0);
    
	// Socket Creation for the client
    if (socketClient != -1) {
		printf("Client Socket Successfully Created!");
	} else {
        perror("Socket creation error from client!\n");
    }

    // Socket Connection for the client (to the server address and port)
	if (connect(socketClient, (struct sockaddr *) &serverAddress, sizeof(serverAddress)) == 0) {
		printf("Connection Successful!\n");
	} else {
		perror("Connection Failure!\n");
	}
}

// Server Mode
void server() {
    // Setting up network variables for the server
    bzero(&serverAddress, sizeof(serverAddress));
    serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(PORT);
	serverAddress.sin_addr.s_addr = htonl(INADDR_ANY);
    socketServerfd = socket(AF_INET, SOCK_STREAM, 0);
    int serverAddressSize = sizeof(serverAddress);

    // Binding the server
	if ((bind(socketServerfd, (struct sockaddr *) &serverAddress, sizeof(serverAddress))) == 0) {
		printf("Binding Successful!\n");
	} else {
		perror("Binding Failure!\n");
	}

    // Listening to the socket connected to the port of choice
	if (listen(socketServerfd, 5) == 0) {
		printf("Server Listening!\n");
	} else {
		perror("Server Listening Failure!\n");
	}

    // Accepting Input on the server socket
	socketServer = accept(socketServerfd, (struct sockaddr *) &clientAddress, &serverAddressSize);
}

/* 
    The two users enter their names 
    Initializing the Connect Four Board
*/
void Initialization() {

    /*  
        We allocate memory for each row and column of the 
        board and initializing each element to 0
    */
    board = malloc(sizeof(char *) * 6);
	for (int i = 0; i < 6; i++){ 
		board[i] = malloc(sizeof(char) * 7);
		for (int j = 0; j < 7; j++){
			board[i][j] = 0;
		}
	}
    
    printf("Setting up the game\n");

    bzero(p1name, sizeof(p1name));
    bzero(p2name, sizeof(p2name));

    /*
        We Accept a name from both the Client and the Server
        The Server is always Player 1
        The Client is always Player 2
    */

    // Client
    if (totalArgs == 3) {
		client();
        // We ask for Player 2's name
        printf("Enter Your Name\n");
        scanf("%s", p2name);

        // Sending Client's name to the Server
        if((send(socketClient, (void *) p2name, sizeof(p2name), 0)) == -1) {
            perror("Failed to send Client Name\n");
        }

        // Receiving Server's name
        if((recv(socketClient, (void *) p1name, sizeof(p1name), 0)) == -1) {
            perror("Client failed to receive server name!\n");
        }

    }
    // Server
	else {
		server();
        
        // We ask for Player 1's name
        printf("Enter Your Name\n");
        scanf("%s", p1name);

        // Receiving Client's name
        recv(socketServer, (void *) p2name, sizeof(p2name), 0);
        
        // Sending Server's name to the Client
        if (send(socketServer, (void *) p1name, sizeof(p1name), 0) == -1) {
            perror("Failed to send server name!\n");
        }     
        
	}
	printf("-------------------------------------------------------------\n");
    
}



/*  
    The two users enter their inputs. This function also
    checks if the inputs are in the right range. 
*/
void AcceptInput() {

    char value[1];
    bzero(value, sizeof(value));


    /*
        If we are in server mode, we are player 1. If it's player 1 turn, we send a value, otherwise we receive
        If we are in client mode, we are player 2. If it's player 2 turn, we send a value, otherwise we receive

        The update and display section Logic should remain the same and it works well
    */
    if (totalArgs == 2) {
        if (playerTurn == 1) {
            printf("%s, Please enter a letter from A-G, or Q to quit!\n", p1name);
            printf("Warning: If multiple letters are entered, only the first character will be used!\n");
            scanf(" %c", &p1input);
            for(;getchar() != '\n';); // This will clear any characters remaining after the first in the line/input variable

            // If the input is not in the accepted range, it will recurse back until the player enters a character in the right range
            if (!((p1input >= 'A' && p1input <= 'G') || (p1input >= 'a' && p1input <= 'g') || p1input == 'Q' || p1input == 'q')) {
                printf("Incorrect input used!\n"); 
                AcceptInput();
            }

            int val;

             /* We pre-check for overflows in the board in the input's column */
            if (p1input >= 'A' && p1input <= 'G') {
                val = p1input - 65;
            } else if (p1input >= 'a' && p1input <= 'g') {
                val = p1input - 97;
            }

            if (board[0][val] != 0) {
                printf("Incorrect input: Board Overflowed! Please try again!\n");
                AcceptInput();
            }

            // We send the value the user has entered
            printf("%s entered: '%c'\n", p1name, p1input);
            value[0] = p1input;
            send(socketServer, value, sizeof(value), 0);

        } else if (playerTurn == 2) {
            // We receive the value the user has entered
            printf("Waiting for %s\n", p2name);
            recv(socketServer, value, sizeof(value), 0);
            p2input = value[0];
            printf("%s entered: '%c'\n", p2name, p2input);
        }
    } else if (totalArgs == 3) {
        if (playerTurn == 1) {
            // We receive the value the user has entered
            printf("Waiting for %s\n", p1name);
            recv(socketClient, value, sizeof(value), 0);
            p1input = value[0];
            printf("%s entered: '%c'\n", p1name, p1input);

        } else if (playerTurn == 2) {
            printf("%s, Please enter a letter from A-G, or Q to quit!\n", p2name);
            printf("Warning: If multiple letters are entered, only the first character will be used!\n");
            scanf(" %c", &p2input);
            for(;getchar() != '\n';); // This will clear any characters remaining after the first in the line/input variable

            // If the input is not in the accepted range, it will recurse back until the player enters a character in the right range
            if (!((p2input >= 'A' && p2input <= 'G') || (p2input >= 'a' && p2input <= 'g') || p2input == 'Q' || p2input == 'q')) { 
                printf("Incorrect input used!\n");
                AcceptInput();
            }
            
            int val;
            
            /* We pre-check for overflows in the board in the input's column */
            if (p2input >= 'A' && p2input <= 'G') {
                val = p2input - 65;
            } else if (p2input >= 'a' && p2input <= 'g') {
                val = p2input - 97;
            }

            if (board[0][val] != 0) {
                printf("Incorrect input: Board Overflowed! Please try again!\n");
                AcceptInput();
            }

            // We send the value the user has entered
            value[0] = p2input;
            printf("%s entered: '%c'\n", p2name, p2input);
            send(socketClient, value, sizeof(value), 0);

        }
    }

    printf("<---------------------- Connect Four ---------------------->\n");
    
}

/* Function to drop the disc in the right coordinates */
int dropDisc(int pt, int input) {

	for (int i = 5; i >= 0; i--) {
		if(board[i][input] == 0){
            board[i][input] = pt;
            return 0;
        }

        /*  
            If the end of the column is reached, we return a 
            negative number to signal the end of the game 
        */
        if(i == 0) {
            return -pt;
        }
	}
	
}

/* 
    Checks if there is a winner by checking for 4 in a row in rows, columns and diagonals.
    All Use Cases are logically covered.
*/
int checkWin(int pt){
    /* Checking the board */
    for(int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
            if (j < 4) {
                
                /* Checking for 4 in a row in Rows */
                if (board[i][j] == pt && board[i][j+1] == pt && board[i][j+2] == pt && board[i][j+3] == pt){
                    return 1;
                }
                
                if (i < 3) {
                    /* Checking for 4 in a row in Columns */
                    if (board[i][j] == pt && board[i+1][j] == pt && board[i+2][j] == pt && board[i+3][j] == pt){
                        return 1;
                    }

                    /* Checking for Diagonals 4 in a row with direction Bottom Right (Also Implies Top Left) */
                    if (board[i][j] == pt && board[i+1][j+1] == pt && board[i+2][j+2] == pt && board[i+3][j+3] == pt) {
                        return 1;
                    }
                } else {
                    /* Checking for Diagonals 4 in a row with direction Top Right (Also Implies Bottom Left) */
                    if (board[i][j] == pt && board[i-1][j+1] == pt && board[i-2][j+2] == pt && board[i-3][j+3] == pt) {
                        return 1;
                    }
                }
            }
        }
    }
    return -1; // Returns -1 if 4 in a row are not found
}

/*
    This Function updates the global states for the players' turns,
    if there is a player who quit the game or if someone made a winning move, 
    and the game loop flag.
*/
void UpdateState() {

    if (playerTurn == 1) {
        
        int dropStatus = -10; // Default value of dropStatus

        /*  
            We get the 'drop status' when we drop a disc into the board, 
            and get a dropStatus number, which we use later to change the
            states of the game.
        */
        if ((p1input >= 'a' && p1input <= 'g')) {
            dropStatus = dropDisc(playerTurn, p1input - 97);
        } else if ((p1input >= 'A' && p1input <= 'G')) {
            dropStatus = dropDisc(playerTurn, p1input - 65);
        } else {  // If the board is overflown, that counts as a forfeit and singals the end of the game
            playerTurn = -1;
            winner = 2;
            flag = 0;
        }

        int checkWinNum = checkWin(playerTurn); // We check if the player won the game

        if (checkWinNum == 1){  // Player 1 Wins if there is Horizontal, Vertical or Diagonal Connect Four Discs
            winner = 1;
            playerTurn = 0;
            flag = 0;
        } else if (checkWinNum == -1 && dropStatus == 0) { // Drop Status of 0, means next player's turn
            playerTurn = 2;     // Goes to Player 1's turn
        } else {
            playerTurn = -1;
            winner = 2;
            flag = 0;
        }



    } else if (playerTurn == 2) {

        int dropStatus = -10; // Default value of dropStatus
        
        /*  
            We get the 'drop status' when we drop a disc into the board, 
            and get a dropStatus number, which we use later to change the
            states of the game.
        */
        if ((p2input >= 'a' && p2input <= 'g')) {
            dropStatus = dropDisc(playerTurn, p2input - 97);
        } else if ((p2input >= 'A' && p2input <= 'G')) {
            dropStatus = dropDisc(playerTurn, p2input - 65);
        } else {   // If the board is overflown, that counts as a forfeit and singals the end of the game
            playerTurn = -2;
            winner = 1;
            flag = 0;
        }

        int checkWinNum = checkWin(playerTurn); // We check if the player won the game
        
        if (checkWinNum == 1){  // Player 2 Wins if there is Horizontal, Vertical or Diagonal Connect Four Discs
            winner = 2;
            playerTurn = 0;
            flag = 0;
        } else if (checkWinNum == -1 && dropStatus == 0) { // Drop Status of 0, means next player's turn
            playerTurn = 1;           // Goes to Player 1's turn
        } else {
            playerTurn = -2;
            winner = 1;
            flag = 0;
        }
    }

}


/*  
    We Display the results of the Game Round based
    on the global states updated in the UpdateState Function 
*/
void DisplayWorld() {

    /* Printinf the Entire Board */
    printf("(A) (B) (C) (D) (E) (F) (G)");
    for (int i = 0; i < 6; i++){
        printf("\n");
		for (int j = 0; j < 7; j++){
			printf("[%d] ", board[i][j]);
		}
	}
    printf("\n\n");

    /*  
        Printing the right result of each move depending on the states
        updated in the updateWorld function 
    */
    if (playerTurn == -1 && winner == 2) {
        printf("%s Quit! %s Wins!\n", p1name, p2name);
    } else if (playerTurn == -2 && winner == 1) {
        printf("%s Quit! %s Wins!\n", p2name, p1name);
    } else if (playerTurn == 0 && winner == 1) {
        printf("%s wins!\n", p1name);
    } else if (playerTurn == 0 && winner == 2) {
        printf("%s wins!\n", p2name);
    } else {
        if (playerTurn == 1) {
            printf("Up Next: %s's turn\n\n\n", p1name);
        } else {
            printf("Up Next: %s's turn\n\n\n", p2name);
        }
        
    }

	

}

/* Ends the Game and Frees the board and network resources */
void Teardown() {
    printf("\nDestroying the game\n");
    close(socketClient);
	close(socketServerfd);
	close(socketServer);
    free(board);
}



/*
    To Compile
        - gcc -o ConnectFour ConnectFour.c
    To Run
        Server
            - ./ConnectFour PORT
            example: 
                ./ConnectFour 5000
        Client
            - ./ConnectFour Address PORT
            example: 
                ./ConnectFour 127.0.0.1 5000
*/
int main(int argc, char ** argv) {

    int port;
    totalArgs = argc;

    // 3 Arguments = Client
    // 2 Arguments = Server
    // Anything else = Program exits
    if (argc == 3) {
        sscanf(argv[2], "%d", &port);
        printf("\nYou're the client!\n");
        PORT = port;
        address = argv[1];
    } else if (argc == 2) {
        sscanf(argv[1], "%d", &port);
        printf("\nYou're the server!\n");
        PORT = port;
    } else {
        printf("Invalid number of arguments! Exiting Program\n");
        exit(1);
    }

    Initialization();
    // The game loop runs infinitely until the game flag changes
    while(flag == 1) {  
        AcceptInput();
        UpdateState();
        DisplayWorld();
    }
    Teardown();
    return 0;
}
