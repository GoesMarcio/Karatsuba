//Projeto e Otimizacao de Algoritmos
//Aluno Marcio Goes
//Professor Joao B. Oliveira
//Trabalho 1 - Karatsuba com Strings

public class Karatsuba{
    public static void main(String[] args){
        String a = args[0];
        String b = args[1];

        System.out.println(removeZ(karatsuba(a, b)));
    }

    public static String sum(String a, String b){
        int n = a.length();
        if(b.length() > n) n = b.length();
        a = completeZ(a, n);
        b = completeZ(b, n);
        
        String r = "";
        boolean carry = false;
        
        for(int i = n-1; i >= 0; i--){
            int aux = a.charAt(i) + b.charAt(i) - (48*2);
            if(carry){
                aux++;
                carry = false;
            }
            if(aux >= 10){
                carry = true;
                aux -= 10; 
            }else{
                carry = false;
            }
            
            r = aux + r;
            
        }
        if(carry) r = "1" + r;
        
        return r;
    }
    
    public static String minus(String a, String b){
        int n = a.length();
        if(b.length() > n) n = b.length();
        a = completeZ(a, n);
        b = completeZ(b, n);
        
        String r = "";
        boolean carry = false;
        
        for(int i = n-1; i >= 0; i--){
            int aux = a.charAt(i) - b.charAt(i);
            if(carry){
                aux--;
                carry = false;
            }
            if(aux < 0){
                carry = true;
                aux += 10; 
            }else{
                carry = false;
            }
            
            r = aux + r;
        }
        if(carry) System.out.println("ERRO!");
        
        return r;
    }
    
    public static String karatsuba(String a, String b){
        int n = a.length();
        if(b.length() > n) n = b.length();
        
        if(n <= 2) return ""+Integer.parseInt(a)*Integer.parseInt(b);
        
        a = completeZ(a, n);
        b = completeZ(b, n);
        
        int shift1 = n;
        int shift2 = n/2;
        if(n%2 != 0){
            shift1 ++;
            shift2 ++;
        }
        
        String a1 = a.substring(0, n/2);
        String a2 = a.substring(n/2, n);
        String b1 = b.substring(0, n/2);
        String b2 = b.substring(n/2, n);
        
        String a1b1 = karatsuba(a1, b1); // tem que shiftar 2*shift
        String a2b2 = karatsuba(a2, b2); // nao shifta
        String sumAux = sum(a1b1, a2b2);
        String a1b2 = minus(karatsuba(sum(a1, a2), sum(b1, b2)), sumAux); // tem que shiftar

        /*
        System.out.println(a1 +" - " + a2 + " --------- " + b1 +" - " + b2);
        System.out.println(sum(sum(shift(a1b1, shift1), shift(a1b2, shift2)), a2b2) + " - "+ shift1); //depuracao do codigo
        */
    
        return sum(sum(shift(a1b1, shift1), shift(a1b2, shift2)), a2b2);
    }
    
    public static String shift(String a, int n){
        for(int i = 0; i<n; i++) a = a + "0";
        return a;
    }
    
    public static String completeZ(String a, int n){
        while(a.length() < n) a = "0" + a;
        return a;
    }

    public static String removeZ(String a){
        int r = 0;
        for(int i = 0; i<a.length();i++){
            if(a.charAt(i) != '0') break;
            r++;
        }
        
        return a.substring(r, a.length());
    }

}