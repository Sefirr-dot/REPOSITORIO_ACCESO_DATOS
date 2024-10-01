package uno;

public class Di_23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fraseString="La lluvia en Sevilla es una maravilla";
		
		System.out.println(fraseString.replace('a', 'e'));
		String fraseFinalString="";
		for (int i = 0; i < fraseString.length(); i++) {
			char letra = fraseString.charAt(i);
			if(letra=='a') {
				fraseFinalString=fraseFinalString+"e";
			} else {
				fraseFinalString=fraseFinalString+letra;
			}
		}
		System.out.println(fraseFinalString);
	}

}
