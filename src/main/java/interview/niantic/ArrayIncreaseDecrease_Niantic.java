package interview.niantic;
//Company: Niantic 3/29/22
/* We have an array that we need to check if this array is first strictly increasing and then strictly decreasing. If so, we're gonna return true, otherwise we're gonna return false.

[0, 1, 2, 3, 2, 1] - true
[0, 1, 3, 3, 6, 1] - false
[3, 2, 1] - false
*/

//2,4,7,12,9,2,1
//Integer array

//1, 5, 6, 8, 4, 3, 2 

//1, 10, 8, 7 -> true
//20 -> false

public class ArrayIncreaseDecrease_Niantic
{
    
    //input array
    public static boolean isIncreaseDecrease(int[] arr)
    {
        //corner case
        if (arr.length == 0 || arr.length == 1) return false;
        if (arr.length == 2)
        {
            return false;
        }
        boolean increase = true;
        boolean decrease = true;
        int i = 0;
        while (i < arr.length-1) //1,2,3
       {
           int a = arr[i]; //1, i = 1 , 2
           int b = arr[i+1];//2, 3
           if (b > a && (i+1) != arr.length-1) // i != 2
           {
               i++; // i=1
           } 
           else 
           {
               break;
           }
       }
       
        while (i < arr.length-1) //2 < 3
       {
           int a = arr[i]; 
           if (i+1 == arr.length) break;
           int b = arr[i+1];
           
           if (b < a) 
           {
               i++;
           } 
            else 
            {
                decrease = false;
            }
       }
       return (increase && decrease);
    
    }
    
    
    public static void main(String[] args) {
        
         int[] arr = new int[]{1,10,8,7};
         boolean b = isIncreaseDecrease(arr);
         System.out.println(b);
        
         int[] arr1 = new int[]{1};
         boolean c = isIncreaseDecrease(arr1);
         System.out.println(c);
        
         int[] arr2 = new int[]{1, 2};
         boolean d = isIncreaseDecrease(arr2);
         System.out.println(d);
        
         int[] arr3 = new int[]{1, 2, 1};
         boolean e = isIncreaseDecrease(arr3);
         System.out.println(e);
        
        int[] arr4 = new int[]{1, 2, 3};
        boolean f = isIncreaseDecrease(arr4);
        System.out.println(f);
        
        
        int[] arr5 = new int[]{0, 1, 3, 3, 6, 1};//false because number is repeated
        boolean g = isIncreaseDecrease(arr5);
        System.out.println(g);
  
    }
}
