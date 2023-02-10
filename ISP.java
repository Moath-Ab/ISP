import java.util.Scanner;
import java.io.File;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;


public class ISP {
    
    public static void main(String [] args){
        
        Scanner input= new Scanner(System.in);
        
      System.out.println("Secret Key Encryption Lab");
      FindfKey();
                   
                   
    }
    
        public static byte[] encryptInput (String input, String key, byte[] IV) {
            
        try {
            
            SecretKeySpec KF = new SecretKeySpec(key.getBytes(), "AES");
            
            IvParameterSpec iv = new IvParameterSpec(IV);
            
            
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            c.init(Cipher.ENCRYPT_MODE, KF, iv);
            
            byte[] output = c.doFinal(input.getBytes());

            return output;
            
      } catch (Exception e1) {
                               } 
                   return null;                     
        }
 
        
        
        public static String decryptInput(byte[] encrypted,  String key, byte[] IV) {

        try {
            
                  
            SecretKeySpec KeySpec = new SecretKeySpec(key.getBytes(), "AES");
            
            IvParameterSpec iv = new IvParameterSpec(IV);
      
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
            c.init(Cipher.DECRYPT_MODE, KeySpec, iv);
            
            byte[] plain = c.doFinal(encrypted);
            
            String X =new String(plain);

                 return X;
            } catch (Exception e1) {
                                   } 
            return null;                                                   }
    
    
        
    public static byte[] ConverHexToArr(String H16) {
        
        int leng = H16.length();
        
        byte[] d = new byte[leng / 2];
        
        for (int n = 0; n < leng; n += 2) {
            
            int index = n / 2; 
            
            d[index] = (byte) ((Character.digit(H16.charAt(n), 16) << 4)
                    
                    + Character.digit(H16.charAt(n + 1), 16));
                                       }
        return d;                
    }

   

    public static String ConvertBytestoHex(byte[] bytes) {
        
        int stop= bytes.length;
        
        char[] convert = "0123456789ABCDEF".toCharArray();
        
        char[] hexChars = new char[bytes.length * 2];
        
        for (int j = 0; j <stop ; j++) {
            
            int x = bytes[j] & 0xFF;
            
            hexChars[j * 2] = convert[x >>> 4];
            
            hexChars[j * 2 + 1] = convert[x & 0x0F];
                                               }
        return new String(hexChars);      
    }
    
    
    
         public static void FindfKey() {
         
        try {
            
            Scanner in = new Scanner(new File("D:\\words.txt"));
            
            
            while (in.hasNextLine()) {
                
                
                String key = in.nextLine();
                
                int kL= key.length();
                
                
                key = key.replace("\n","");
                
                
                if ( kL < 16) {
                    for (int i =  kL ; i < 16; i++) {
                        
                        key= key+ "#";
                                       } }
                
                
                String plaintext = "This is a top secret.";
                
                byte[] iv = ConverHexToArr("aabbccddeeff00998877665544332211");
                
                byte[] R = encryptInput(plaintext, key, iv);
                
                if (R != null) {
                    String EH = ConvertBytestoHex(R);

                if (EH.toLowerCase().equals("764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2")) 
                {
                        System.out.println("Find the KEY for my texts");
                        
                        String plain = decryptInput(R, key, iv);

                if (plain != null)
                {
                            
System.out.println("the key used for this text is:"+key+", and the plainText is"+plain);
                            
                            
                        
     System.out.println("the encrypted text in Hexa: " + EH);
                        } else {
                            System.out.println("the key does not found");
                                }                                                                                   }
                                       }
                                   } 
            }
        catch (Exception a1) {
            a1.printStackTrace();
                              }            
                                   }
    
    
    
}