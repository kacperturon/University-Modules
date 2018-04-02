	;Stack and Stack Pointer Addresses - Necessary for subroutines
		.equ     SPH    =$3E              //High Byte Stack Pointer Address 
		.equ     SPL    =$3D              //Low Byte Stack Pointer Address 
		.equ     RAMEND =$25F             //Stack Address 

	;Port Addresses 
		;INPUT
			;In-board STK200 switches/push-buttons
		.equ     DDRA   =$1A               //Port A Data Direction Register Address 
		.equ     PINA   =$19               //Port A Input Address 
		;OUTPUT
			;7 Segment LED display
		.equ     PORTB  =$18             //Port B Output Address 
		.equ     DDRB  =$17              //Port B Data Direction Register Address 
			;Switch light box LEDs
		.equ     PORTC  =$15             //Port C Output Address 
		.equ     DDRC   =$14             //Port C Data Direction Register Address 
			;Buzzer
		.equ     PORTD  =$12             //Port D Output Address 
		.equ     DDRD   =$11             //Port D Data Direction Register Address 

	;Register Definitions 
		.def     leds   =r0               //Register to store data for LEDs 
		.def     SevenSegmentDisplay=r11  //Register to store data pointed to by Z 
		.def     temp   =r16              //Temporary storage register 
		.def     ZL     =r30              //Define low byte of Z 
		.def     ZH     =r31              //Define high byte of Z 

	;Set stack pointer to end of memory #Used for subroutines
		ldi    temp,high(RAMEND) 
		out    SPH,temp          //Load high byte of end of memory address 
		ldi    temp,low(RAMEND) 
		out    SPL,temp          //Load low byte of end of memory address 
	
	;Initialise input ports
 		ldi    temp,$00 
        out    DDRA,temp         //Set Port A for input by sending $00 to direction register 
	
	;Initialise output ports
		ldi    temp,$ff 
        out    DDRB,temp         //Set Port B for output by sending $FF to direction register 
		ldi    temp,$ff	
	    out    DDRC,temp         //Set Port C for output by sending $FF to direction register
		ldi    temp,0b00001000
    	out    DDRD,temp         //Set Port D pin 3 for output by sending $08 to direction register
	
restartGame:		
	;registers - description
		;r0/leds - used for the position on switch light box LED
		;r1 - $0  //0-84    - Aircraft Carrier //0-127 	- Left  //Used also to check if a register is empty
		ldi temp,$66
		mov r2, temp //Seed value for Pseudo Random Number Generation -  0110 0110
		;r3,r4 - used as temporary registers for number generation
		clr r5 // how many times overall has user missed
		ldi temp,$03
		mov r6, temp //value used to check if user has already missed 3 times if so reset the game
		;r7 - used to check if user has already shot a torpedo this turn If 1 - user already shot the torpedo 0 - user didn't shot a torpedo yet
		ldi temp, $55
		mov r8,temp //85-169  - Battleship
		ldi temp,$AA
		mov r9,temp  //170-255 - Destroyer
		ldi temp,$80
		mov r10,temp //128-255	- Right
		;r11 - Register to store data pointed to by Z - current 7 segment LED display register
		;r12 - First number from score - most important character
		;r13 - Second number from score - least important character
		;r14 - length of buzzer's noise
		;r16 - used as a temporary register for many arthemtic operations throughout the code
		;r17 - used for input register port A
		ldi r18,$0 //if 1 - Destroyer 2 - Battleship 3 - Aircraft Carrier
		ldi r19,$0 //if 1 - Left 2 - Right
		ldi temp,$64 
		mov	r20,temp //This value is copied to r23 (3rd loop) - Manipulated to speed up or slow down the game 		
		;r21, r22, r23 - used as 1st and 2nd and 3rd loop counter respectively
		ldi r24,$00 //Initial Game score
		rcall separateNibbles
		;r25 - Static loop initialisation
		;r26,r27 - 1s and 2nd loop counter for buzzer 
		;r30, r31 - low and high byte of Z
   
    ;========================================GAME LOOP========================================;
					gameLoop:	;ldi r18, $01 //Use to force a certain ship for testing
								rcall random	//generate random number for ship
								rcall ship		//get random ship from generated number
								;ldi r19, $02 //Use to force which direction ship should move for testing
								rcall random  //generate random number for direction
								rcall direction	//get random direction from generated number
								clr r7 //Reset user missed torpedo value								
								rcall spawnShip //initialize ship on switch light box LEDs
								cpi r19,$01
								breq moveLeft 
								cpi r19,$02
								breq moveRight

						moveLeft:   lsl leds //Rotate left leds register
									out PORTC,leds       //Display leds to port C 
									rcall delayCheck
									cp leds,r1			//If register is empty the ship escaped from the player hence exit the loop and spawn a new ship
									breq gameLoop
									rjmp moveLeft

						moveRight:  lsr leds //Rotate right leds register
									out PORTC,leds       //Display leds to port C 
									rcall delayCheck
									cp leds,r1			//If register is empty the ship escaped from the player hence exit the loop and spawn a new ship
									breq gameLoop
									rjmp moveRight

	table:   .DB $3F,$06,$5B,$4F,$66,$6D,$7D,$07, $7F, $6F, $77, $7C, $39, $5E, $79, $71 //Table consisting of numbers in hexadecimal 0-F on 7 Segment display

 	;========================================GAME LOOP========================================;

	;Subroutines

	//**Generate Random Number**
	random:	mov r3, r2  //Copy value of seed r2 to r3
			mov r4, r2  //Copy value of seed r2 to r3	
			ldi temp,$01
			and r3,temp  //Get get last bit from r2
			ldi temp,$02
			and r4,temp  //Get second to last bit from r2
			lsr  r4      //Moving second to last bit from r2 to LSB of r4
			eor r3,r4 //XOR last two bits together from r2
			;Moving LSB to the MSB 
			swap r3
			lsl r3
			lsl r3 
			lsl r3
			lsr r2
			or r2, r3 //adding r17 MSB onto the MSB of r16 
			ret
	//Generate Random Number

	//**Assign Ship**
	ship:	cp r2,r9
			brsh assignD
			cp r2,r8
			brsh assignBS
			cp r2,r1
			brsh assignAC
			ret 

		assignAC:	ldi r18, $03
					ret
		assignBS:	ldi r18, $02
					ret
		assignD:	ldi r18, $01
					ret
	//Assign Ship


	//**Assign Direction**
	direction:	cp r2,r10	
				brsh assignRight
				cp r2,r1
				brsh assignLeft
				ret

		assignLeft:	ldi r19,$01 //Add 1 to r19
					ret
		assignRight:ldi r19,$02 //Add 2 to r19
					ret		
	//Assign Direction

	//**Spawn Ship** - Initialising ship on switch light box LEDs (in phases every aircraft carrier is initially destroyer on frame 1 [1 LED] then it is a battleship on frame 2 [2 LEDs] on frame 3 it is a full aircraft carrier [3 LEDs])
	spawnShip:	cpi r19,$1
				breq spawnShipLeftD //Spawn ship on the right hand side with direction left
				cpi r19,$2
				breq spawnShipRightD //Spawn ship on the left hand side with direction right

		spawnShipLeftD:   	clr leds
							out PORTC, leds
							rcall delayRandom
							ldi temp, 0b00000001
							mov leds, temp
							out PORTC, leds
							rcall delayCheck
							cpi r18,$2  //If the ship is a battleship increment the size by 1
							breq spawnBattleShipLeftD
							cpi r18,$3  //If the ship is a aircraft carrier increment the size by 3 
							breq spawnAircraftCarrierLeftD
							ret

			spawnBattleShipLeftD:ldi temp, 0b00000011
								 mov leds, temp 
								 out PORTC, leds
								 rcall delayCheck
								 ret

		spawnAircraftCarrierLeftD:rcall spawnBattleShipLeftD
								 ldi temp, 0b00000111
								 mov leds, temp
								 out PORTC, leds
								 rcall delayCheck
								 ret

		spawnShipRightD:    clr leds
							out PORTC, leds
							rcall delayRandom
							ldi temp, 0b10000000
							mov leds, temp
							out PORTC, leds
							rcall delayCheck
							cpi r18,$2  //If the ship is a battleship increment the size by 1
							breq spawnBattleShipRightD
							cpi r18,$3  //If the ship is a aircraft carrier increment the size by 3 
							breq spawnAircraftCarrierRightD
							ret

			spawnBattleShipRightD:clr leds	//clr leds hence was modified to 0x80 from 0x01 so incrementing that value would destroy our ship
								 ldi temp, 0b11000000 
								 mov leds, temp
								 out PORTC, leds
								 rcall delayCheck
								 ret

			spawnAircraftCarrierRightD:rcall spawnBattleShipRightD //Every aircraft carrier is a battleship during 2nd frame of initialisation on LED switch light box
								 ldi temp, 0b11100000
								 mov leds, temp
								 out PORTC, leds
								 rcall delayCheck
								 ret
	//Spawn Ship

	//*Check Press* - check button was pressed, restrict user to pressing only one buttone at once by checking register with powers of 2
	checkPress: 			ldi temp, $01
							cp r7,temp //Check if user missed this turn
							breq checkButtons //If user missed this turn we invert r17 to be always false so it will check again methods below which won't speed up the ship after missing
							in r17, PINA  //put the pin A value in r17
			checkButtons:	com r17   //basic state of switches is 1111 1111 so invert to get 0000 0000
							cpi r17,$01
							breq press  //If r17 is 0xb0000 0001
							cpi r17,$02
							breq press  //If r17 is 0xb0000 0010 
							cpi r17,$04
							breq press  //If r17 is 0xb0000 0100 
							cpi r17,$08
							breq press  //If r17 is 0xb0000 1000
							cpi r17,$10
							breq press  //If r17 is 0xb0001 0000
							cpi r17,$20
							breq press  //If r17 is 0xb0010 0000 
							cpi r17,$40
							breq press  //If r17 is 0xb0100 0000 
							cpi r17,$80
							breq press  //If r17 is 0xb1000 0000
				missedReturn:ret
			
							press: 	and r17, leds  //It will only be equal to more than 0 if the ship was shot
									cp r17, r1  //If r17 isn't empty the ship got shot down
									brne shotDown
									cp r17, r1
									breq missed

								shotDown:	rcall buzzer
											clr leds //Destroy the ship
											out PORTC, leds 
											cpi r18, $01
											breq scoreDestroyer
											cpi r18, $02
											breq scoreBattleship
											cpi r18, $03
											breq scoreAircraftCarrier
								scoreAdded:	rcall delayRandom
											cpi r20, $05 //If r20 == 05 skip subtracting keep the speed of 05
											breq skipSpeedUp	
											subi r20, $05//Speed up the game
							skipSpeedUp:	rjmp gameLoop //Spawn a new ship
								missed:		inc r7 //Has user missed this turn
											inc r5 //Increment overall times user missed
											cp r5,r6
											breq restart //If user missed 3 times start over
											rjmp missedReturn

										restart:	ldi temp, 0b11111111 //Short losing animation
													out PORTC, temp
													ldi temp, $04
													rcall delay
													ldi temp, 0b00000000
													out PORTC, temp
													ldi temp, $04
													rcall delay
													ldi temp, 0b11111111
													out PORTC, temp
													ldi temp, $04
													rcall delay
													ldi temp, 0b00000000
													out PORTC, temp
													ldi temp, $04
													rcall delay
													ldi temp, 0b11111111
													out PORTC, temp
													ldi temp, $02
													rcall delay
													ldi temp, 0b01111110
													out PORTC, temp
													ldi temp, $02
													rcall delay
													ldi temp, 0b00111100
													out PORTC, temp
													ldi temp, $02
													rcall delay
													ldi temp, 0b00011000
													out PORTC, temp
													ldi temp, $02
													rcall delay
													rjmp restartGame

	scoreDestroyer: ldi temp,$0C
					add r24, temp
					rcall separateNibbles
					rjmp scoreAdded
	scoreBattleship:ldi temp, $08
					add r24,temp
					rcall separateNibbles
					rjmp scoreAdded
	scoreAircraftCarrier:ldi temp, $04 
					add r24, temp
					rcall separateNibbles
					rjmp scoreAdded						
	//Check Press

	//*Separate Nibbles*	
	separateNibbles:mov r12, r24 //Copy score into r12
					mov r13, r24 //Copy score into r13
					ldi temp, 0b11110000 
					and r12, temp //Kepp only left nibble of score in r12
					swap r12 //Swap nibbles so values of both r12, r13 are at the same bit positions
					ldi temp, 0b00001111 
					and r13, temp //Keep only right nibble of score in r13
					ret
	//Separate Nibbles

	//*Reset Pointer*	
	resetPointer:ldi    ZL,low(table*2)   //Set Z pointer to start of table 
			     ldi    ZH,high(table*2) 
			     ret
    //Reset Pointer

	//*Display Score*
	DisplayScore:    ;Displaying left digit 
				     rcall resetPointer //Set the pointer to the start of the table
					 ldi temp,$0
					 cp r12,temp
					 breq skip //Check if the left digit is $0 - default pointer position, if so skip the loop
					 mov temp, r12
		tableLoop1:  adiw ZL,1              //Increment Z to point to next location in table 
					 dec temp
					 brne tableLoop1
				skip:lpm SevenSegmentDisplay, Z	 //Load program memory into r11	
					 out PORTB,SevenSegmentDisplay        //and display data on port B
				     rcall delaySmallStatic
				     ;Displaying right digit
					 rcall resetPointer
					 ldi temp, $0
					 cp r13,temp
					 breq skip2 //We want to skip loop when r21 is 0, because by default pointer points to 0
					 mov temp, r13			 
		tableLoop2:  adiw ZL,1              //Increment Z to point to next location in table 
					 dec temp
					 brne tableLoop2
				skip2:lpm SevenSegmentDisplay, Z //Load program memory into r11
					 ldi temp,0b10000000 
					 or SevenSegmentDisplay, temp //Displaying right bit - r22, by changing MSB to 1		
					 out PORTB,SevenSegmentDisplay        //and display data on port B 
					 ret  
	//Display Score

	//*Buzzer*
	buzzer:	ldi temp,$1F
			mov r14,temp //Length of buzzer's noise
buzzerLoop:	ldi temp, $08
			out PORTD, temp
			rcall delayBuzzer
			rcall DisplayScore
			ldi temp, $00
			out PORTD, temp
			rcall delayBuzzer
			rcall DisplayScore	
			dec r14
			brne buzzerLoop
			ret
	//Buzzer

	//*Delays*
	;(from 85.327 ms to 21.757369 s @ 1Mhz)
		delay:	 mov    r23,temp			//Copy value of temp into r23     
		loop3:	 ldi    r22,$36         //Initialise 2nd loop counter 
		loop2:   ldi    r21,$FE          //Initialise 1st loop counter
		loop1:   dec    r21              //Decrement the 1st loop counter 
				 brne   loop1            //and continue to decrement until 1st loop counter = 0 
		         rcall DisplayScore
				 dec    r22              //Decrement the 2nd loop counter 
		         brne   loop2            //If the 2nd loop counter is not equal to zero repeat the 1st loop, else continue 
				 dec	r23				 //Decrement the 3rd loop counter
				 brne	loop3			 //If the 3rd loop counter is not equal to zero repeat the 2nd loop, else continue
		       	 ret
	;(Initial delay from 4.414 ms to 1.124554 s @ 1Mhz)
	delayCheck:	 mov    r23,r20		//Copy value of r20 into r23     
		loop6:	 ldi    r22,$03         //Initialise 2nd loop counter 
		loop5:   ldi    r21,$15           //Initialise 1st loop counter 
		loop4:   rcall checkPress		 //Check if user shot torpedo
			     dec    r21              //Decrement the 1st loop counter 
				 brne   loop4            //and continue to decrement until 1st loop counter = 0 
				 rcall DisplayScore
		         dec    r22              //Decrement the 2nd loop counter 
		         brne   loop5            //If the 2nd loop counter is not equal to zero repeat the 1st loop, else continue 
				 dec	r23				 //Decrement the 3rd loop counter
				 brne	loop6			 //If the 3rd loop counter is not equal to zero repeat the 2nd loop, else continue
		       	 ret
	;(Random from 9.523ms to 2.417443 s @ 1Mhz)
	delayRandom: rcall random
				 ldi    r23,$06			//Copy value of  into r23     
		loop9:	 mov    r22,r2          //Initialise 2nd loop counter 
		loop8:   ldi    r21,$FE           //Initialise 1st loop counter 
		loop7:   dec    r21              //Decrement the 1st loop counter 
				 brne   loop7            //and continue to decrement until 1st loop counter = 0 
		         rcall DisplayScore
				 dec    r22              //Decrement the 2nd loop counter 
		         brne   loop8            //If the 2nd loop counter is not equal to zero repeat the 1st loop, else continue 
				 dec	r23				 //Decrement the 3rd loop counter
				 brne	loop9			 //If the 3rd loop counter is not equal to zero repeat the 2nd loop, else continue
		       	 ret
	
	;(769 us @ 1MHz)
 delaySmallStatic:ldi    r25,$FF      //Initialise decrement counter 
	 	loop10:	  dec    r25        //Decrement counter 
	              brne   loop10      //and continue to decrement until counter = 0 
	              ret              //Return

	;(1.915 ms @ 1Mhz)
	delayBuzzer:   ldi r27, $07 
			loop12:ldi r26, $5A 
			loop11:dec r26
				   brne loop11
				   dec r27
				   brne loop12
				   ret
	//Delay

	    
		