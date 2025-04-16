package utils;

public class ValidarCPF {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        char dig10, dig11;
        int sm, r, num, peso;

        try {
            // Cálculo do 1º dígito verificador
            sm = 0;
            peso = 10;
            for (int i = 0; i < 9; i++) {
                num = cpf.charAt(i) - '0'; // conversão de char para int
                sm += (num * peso--);
            }

            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char)(r + '0');

            // Cálculo do 2º dígito verificador
            sm = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                num = cpf.charAt(i) - '0';
                sm += (num * peso--);
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char)(r + '0');

            return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);
        } catch (Exception e) {
            return false;
        }
    }

    public static String formatCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
