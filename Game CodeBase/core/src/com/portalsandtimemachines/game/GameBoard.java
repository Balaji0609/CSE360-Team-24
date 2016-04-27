package com.portalsandtimemachines.game;

import java.util.*;

/**
 * 	Class which contains methods for setting up and computing the logic behind
 *  the portals and time machines.
 *  
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 *
 */
public class GameBoard {
	
	    HashMap<Integer, List<Integer>> portals_hashMap = new HashMap<Integer, List<Integer>>();
	    int[] portal_array = new int[8];
	    int i_index = 0;
	    
	   /** Function that sets the position of the portals for the 
	    * duration of the game.
	    * 
	    * @param none
	    */
	    void init()
	    {
	        for( int i_index = 0;i_index < 8;i_index++)           // There will be 8 portals in the Game board.
	        {
	            set_portals();
	        }
	        System.out.println("Initializing the portal values");
	    }
	    
	    int roll_die()
	    {
	    	Random rand = new Random();
	    	return rand.nextInt((6 - 1) + 1) + 1;
	    }
	    
	   /** Function that displays the information regarding all the portals. 
	    *  Used by developers for verification.
	    *  
	    * @param none
	    */
	    void print()
	    {
	        for(int key : portals_hashMap.keySet())
	        {
	            System.out.println(key + " : " +portals_hashMap.get(key));
	        }
	    }
	    
	    
	    /**
	     * Function that returns a array which contains the position of the portals on the board.
	     * 
	     * @return int[] returns the array of position of portals.
	     */
	    int[] portal_positions()
	    {
	        return portal_array;
	    }
	    
	   /** Function that computes the position of each portal in the game board and their c
	    *  corresponding attributes. Their attributes correspond to information such as 
	    *  whether the portal will push down(-1) or up(+1), how many squares will the pawn 
	    *  be moved and final square in which the pawn will land.
	    *  
	    * @param none
	    */
	    void set_portals()
	    {
	        Random rand = new Random();
	        int portal_pos;
	        int orientation;
	        int moving_spaces = 0;
	        int final_pos = 0;
	        
	        portal_pos = rand.nextInt((97 - 2) + 1) + 2;
	 
	        while(portals_hashMap.containsKey(portal_pos))         // If the position already exist in the map.
	        {
	            portal_pos = rand.nextInt((97 - 2) + 1) + 2;
	        }
	            
	        if(portal_pos < 10 && portal_pos > 2 )
	            orientation = 1;
	        else if(portal_pos <= 97 && portal_pos >= 90) 
	            orientation = -1;
	        else
	        {
	            int temp = rand.nextInt((1 - 0) + 1) + 0;
	            if(temp == 0)
	                orientation = 1;
	            else
	                orientation = -1;
	        }
	            
	        int check = 0;
	        while(check == 0)                           // Flag to check whether the resulting square is within range of the Game board.
	        {
	            int moving = rand.nextInt((30 - 5) + 1) + 5;
	            int sum;
	            if(orientation == 1)
	                sum = portal_pos + moving;
	            else
	                sum = portal_pos - moving;
	            
	            if(sum < 98 && sum > 2)
	            {
	                moving_spaces = moving;
	                final_pos = sum;
	                check = 1;
	            }
	        }
	        List<Integer> temp_list = new ArrayList<Integer>();
	        temp_list.add(orientation);
	        temp_list.add(moving_spaces);
	        temp_list.add(final_pos);
	        portals_hashMap.put(portal_pos,temp_list);
	        portal_array[i_index] = portal_pos;
	        i_index++;
	    }
	    
	    
	   /** Function that checks whether the passed square number has a portal 
	    *  associated with it. If it is true then the function returns the 
	    *  square number to which the pawn needs to be moved, else it returns -1.
	    *  
	    * @param position of the square.
	    */
	    int check_portal(int position)
	    {
	        if(portals_hashMap.containsKey(position))
	        {
	            List<Integer> temp_list;
	            for(int key : portals_hashMap.keySet())
	            {
	                if(key == position)
	                {
	                    temp_list = portals_hashMap.get(position);
	                    System.out.println("Coding");
	                    System.out.println(temp_list.get(2));
	                    return temp_list.get(2);             // Returns the position to which the pwan needs to be moved.
	                }
	            }
	        }
	        return 0;                                  //  If the square does not have a portal associatd with it.
	    }
	    
	   
	    int[] bounty_array = new int[3];
	    int k_index;
	    void init_bounty()
	    {
	    	for( int i_index = 0; i_index < 3; i_index++ )
	    	{
	    		set_bounty();
	    	}
	    	System.out.println("Initializing the Bounty Positions values");
	    }
	    
	    
	    int[] bounty_positions()
	    {
	    	return bounty_array;
	    }
	    
	    
	    void set_bounty()
	    {
	    	Random rand = new Random();
	    	int flag = 0;
	    	int position;
	    	position = rand.nextInt((89 - 12) + 1) + 12;
	    	while(flag == 0)
	    	{
	    		flag = 1;
	    		position = rand.nextInt((89 - 12) + 1) + 12;
	    		for(int check_index = 0; check_index < 8; check_index++)
	    		{
	    			if(position == portal_array[check_index])
	    			{
	    				flag = 0;
	    			}
	    		}
	    		
	    		for(int check_index = 0; check_index < 3; check_index++)
	    		{
	    			if(position == timeMachine_array[check_index])
	    			{
	    				flag = 0;
	    			}
	    		}
	    	}
	    	
	    	bounty_array[k_index] = position;
	    	k_index++;
	    }
	    
	    int check_bounty(int position)
	    {
	    	for(int k_index = 0; k_index < 3; k_index++)
	    	{
	    		if(position == bounty_array[k_index])
	    		{
	    			return 1;
	    		}
	    	}
	    	return 0;
	    }
	    
	    HashMap<Integer, List<Integer>> timeMachine_hashMap = new HashMap<Integer, List<Integer>>();
	    int[] timeMachine_array = new int[8];
	    int j_index = 0;
	    
	   /** Function that sets the position of the time-machine for the 
	    *  duration of the game.
	    * 
	    *  @param none
	    */
	    void init_time_machine()
	    {
	        for( int i_index = 0;i_index < 3;i_index++)           // There will be 3 time machine in the Game board.
	        {
	            set_timemachine();
	        }
	        System.out.println("Initializing the Time Machine values");
	    }
	    
	    
	    /**
	     * Function that returns a array which contains the position of the time machine on the board.
	     * 
	     * @return int[] returns the array of position of time machine.
	     */
	    int[] timeMachine_positions()
	    {
	    	
	        return timeMachine_array;
	    }
	    
	   /** Function that computes the position of each time-machine in the game board and their
	    *  corresponding attributes. Their attributes correspond to information such as 
	    *  whether the portal will push up(+1), how many squares will the pawn 
	    *  be moved and final square in which the pawn should land.
	    *  
	    *  @param none
	    */
	    void set_timemachine()
	    {
	        Random rand = new Random();
	        int timeMachine_pos;
	        int orientation;
	        int moving_spaces = 0;
	        int final_pos = 0;
	        
	        timeMachine_pos = rand.nextInt((99 - 12) + 1) + 12;
	 
	        while(timeMachine_hashMap.containsKey(timeMachine_pos) || portals_hashMap.containsKey(timeMachine_pos))         // If the position already exist in the map and it don't clash with portal.
	        {
	            timeMachine_pos = rand.nextInt((99 - 12) + 1) + 12;
	        }
	            
	        orientation = 1;
	        
	        int check = 0;
	        while(check == 0)                           // Flag to check whether the resulting square is within range of the Game board.
	        {
	            int moving = rand.nextInt(4) + 4;
	            int sum = 0;
	            sum = timeMachine_pos + moving;
	            
	            if(sum < 99 && sum > 2)
	            {
	                moving_spaces = moving;
	                final_pos = sum;
	                check = 1;
	            }
	        }
	        List<Integer> temp_list = new ArrayList<Integer>();
	        temp_list.add(orientation);
	        temp_list.add(moving_spaces);
	        temp_list.add(final_pos);
	        timeMachine_hashMap.put(timeMachine_pos,temp_list);
	        timeMachine_array[j_index] = timeMachine_pos;
	        j_index++;
	    }
	    
	   /** Function that checks whether the passed square number has a time-machine 
	    *  associated with it. If it is true then the function returns the 
	    *  square number to which the pawn needs to be moved, else it returns 0.
	    *  
	    *  @param position of the square.
	    */
	    int check_timeMachine(int position)
	    {
	        if(timeMachine_hashMap.containsKey(position))
	        {
	            List<Integer> temp_list;
	            for(int key : timeMachine_hashMap.keySet())
	            {
	                if(key == position)
	                {
	                    temp_list = timeMachine_hashMap.get(position);
	                    return temp_list.get(2);             // Returns the position to which the pawn needs to be moved.
	                }
	            }
	        }
	        return 0;                                  //  If the square does not have a portal associated with it.
	    }
}
