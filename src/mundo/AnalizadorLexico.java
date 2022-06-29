/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Dise�o original por: Leonardo A. Hern�ndez R. - Agosto 2008 - Marzo 2009
 * Modificado y usado por: Claudia E. Quiceno R- Julio 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package mundo;

import java.util.ArrayList;


/**
 * Clase que modela un analizador l�xico
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un c�digo fuente dado
	 * @param cod - c�digo al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens( String cod )
	{
		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posici�n cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while( i < cod.length() )
		{
			// Extrae el token de la posici�n i
			token = extraerSiguienteToken( cod, i);
			vectorTokens.add( token );
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posici�n i, bas�ndose en el Aut�mata
	 * @param cod - c�digo al cual se le va a extraer un token - codigo!=null
	 * @param i - posici�n a partir de la cual se va a extraer el token  - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken( String cod, int i )
	{
		Token token;

		//Mafe

		token = extraerOperadorMenorIgual(cod, i);
		if(token!=null)
			return token;

		token= extraerOperadorMayorIgual(cod, i);
		if(token!=null)
			return token;

		token = extraerOperadorMenorQue(cod, i);
		if(token!=null)
			return token;

		token = extraerOperadorMayorQue(cod, i);
		if(token!=null)
			return token;

		token = extraerOperadorDiferente(cod, i);
		if(token!=null)
			return token;

		token=extraerOperadorLogicoY(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorLogicoO(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorLogicoNegacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorSumaAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorRestaAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorMultAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorDivAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorModAsignacion(cod, i);
		if (token != null)
			return token;

		token = extraerOperadorAsignacion(cod, i);
		if(token!=null)
			return token;

		token = extraerOperadorIgual(cod, i);
		if(token!=null)
			return token;

		token = extraerSimboloAbrir(cod, i);
		if (token != null)
			return token;

		token = extraerSimboloCerrar(cod, i);
		if (token != null)
			return token;

		//Santiago

		token =extraerTipoEntero(cod, i);
		if ( token != null )
			return token;


		token =extraerTipoReal(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoCadena(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoCaracter(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoModificadorAcceso(cod, i);
		if ( token != null )
			return token;

		token =extraerNombreClase(cod, i);
		if ( token != null )
			return token;

		//Alexis

		token =extraerTipoSuma(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoResta(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoDivision(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoMultiplicacion(cod, i);
		if ( token != null )
			return token;

		token =extraerTipoModulo(cod, i);
		if ( token != null )
			return token;

		token =extraerAsignacionEntero(cod, i);
		if ( token != null )
			return token;

		token =extraerAsignacionReal(cod, i);
		if ( token != null )
			return token;

		token =extraerAsignacionCadenas(cod, i);

		if ( token != null )
			return token;

		token =extraerAsignacionCaracter(cod, i);
		if ( token != null )
			return token;

		// Lopez Juan Jose
		token = extraerSeparadorSentencias(cod, i);
		if ( token != null )
			return token;
		
		token = extraerBucle(cod, i);
		if ( token != null )
			return token;
		
		token = extraerDecision(cod, i);
		if ( token != null )
			return token;
		
		token = extraerClaseTipo(cod, i);
		if ( token != null )
			return token;
		
		token = extraerNombresVariables(cod, i);
		if ( token != null )
			return token;
		
		token = extraerNombresMetodos(cod, i);
		if ( token != null )
			return token;
		
		token = extraerInicialLinea(cod, i);
		if ( token != null )
			return token;
		
		// Extrae un token no reconocido
		token = extraerNoReconocido( cod, i);
		return token;
	}
	
	/**
	 * Intenta extraer una palabra que califique como el nombre de un m�todo de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerNombresMetodos(String cod, int i) {
		int j;
		String lex;
		if( esMinuscula(cod.charAt(i)) ){
			j=i+1;
			if( j<cod.length() && esLetra(cod.charAt(j)) ){		
				do
					j++;
				while (  j<cod.length( ) && esLetra(cod.charAt(j)) );
				if ( j<cod.length() && esDigito(cod.charAt(j))) {
					j++;
					lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.METODOS, j );
					return token;	
				}		
			} else if ( j<cod.length() && esDigito(cod.charAt(j))) {
				j++;
				lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.METODOS, j );
				return token;	
			} 
		}
		return null;
	}

	/**
	 * Intenta extraer una palabra que califique como una variable de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como una variable. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerNombresVariables(String cod, int i) {
		int j;
		String lex;
		if( esMayuscula(cod.charAt(i)) ){
			j=i+1;
			if( j<cod.length() && esLetra(cod.charAt(j)) ){		
				do
					j++;
				while (  j<cod.length( ) && esLetra(cod.charAt(j)) );
				if ( j<cod.length() && esDigito(cod.charAt(j))) {
					j++;
					lex =  cod.substring( i, j);			    
					Token token = new Token( lex, Token.VARIABLES, j );
					return token;	
				}		
			} else if ( j<cod.length() && esDigito(cod.charAt(j))) {
				j++;
				lex =  cod.substring( i, j);			    
				Token token = new Token( lex, Token.METODOS, j );
				return token;	
			} 
		}
		return null;
	}

	/**
	 * Intenta extraer una palabra que indica una clase de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que indica una clase. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerClaseTipo(String cod, int i) {
		int j;
		String lex;
		if( cod.charAt(i)=='t' ){
			j=i+1;
			if ( cod.charAt(j)=='i' ) {
				j++;
				if ( cod.charAt(j)=='p' ) {
					j++;
					if ( cod.charAt(j)=='o' ) {
						j++;
						lex =  cod.substring( i, j);			    
						Token token = new Token( lex, Token.CLASETIPO, j );
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una palabra que indica el inicio de una decisi�n o ciclo de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que indica el inicio de una decisi�n. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerDecision(String cod, int i) {
		int j;
		String lex;
		if( cod.charAt(i)=='d' ){
			j=i+1;
			if ( cod.charAt(j)=='e' ) {
				j++;
				if ( cod.charAt(j)=='c' ) {
					j++;
					if ( cod.charAt(j)=='i' ) {
						j++;
						if ( cod.charAt(j)=='d' ) {
							j++;
							if ( cod.charAt(j)=='e' ) {
								j++;
								lex =  cod.substring( i, j);			    
								Token token = new Token( lex, Token.DECISION, j );
								return token;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer una palabra que indica el inicio de un bucle o ciclo de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que indica el inicio de un bucle o ciclo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerBucle(String cod, int i) {
		int j;
		String lex;
		if( cod.charAt(i)=='c' ){
			j=i+1;
			if ( cod.charAt(j)=='i' ) {
				j++;
				if ( cod.charAt(j)=='c' ) {
					j++;
					if ( cod.charAt(j)=='l' ) {
						j++;
						if ( cod.charAt(j)=='o' ) {
							j++;
							lex =  cod.substring( i, j);			    
							Token token = new Token( lex, Token.BUCLE, j );
							return token;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un inicial de l�neas de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *        iniciador de l�neas. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */

	private Token extraerInicialLinea(String cod, int i) {
		int j;
		String lex;
		if( cod.charAt(i)=='@' ){
			j=i+1;
			lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.INICIAL, j );
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un separador de sentencias de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *        separador de sentencias. El Token se compone de el lexema, el tipo y la
	 *        posici�n del siguiente lexema.
	 */

	private Token extraerSeparadorSentencias(String cod, int i) {
		int j;
		String lex;
		if( cod.charAt(i)=='~' ){
			j=i+1;
			lex =  cod.substring( i, j);			    
			Token token = new Token( lex, Token.SEPARADORSENTENCIAS, j );
			return token;
		}
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para la asignacion de caracteres de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerAsignacionCaracter(String cod, int i) {
		int j = 0;
		String lex;
		if(esLetra(cod.charAt(i))) {
			j=i+1;

			if(j < cod.length() && cod.charAt(j)=='�' ) {
				j++;

				if(j < cod.length() && cod.charAt(j)=='C' ) {
					j++;

					if(j < cod.length() && cod.charAt(j)=='�'  ) {
						j++;
							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.OPERADORCARACTER, j);
							return token;
						
					}
				}

			}
		}
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para la asignacion de cadenas de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerAsignacionCadenas(String cod, int i) {
		int j = 0;
		String lex;
		if(esLetra(cod.charAt(i))) {
			j=i+1;

			while( j < cod.length() && esLetra(cod.charAt(j)) ) {
				j++;
			}

			if(j < cod.length() && cod.charAt(j)=='"' ) {
				j++;

				if(j < cod.length() && cod.charAt(j)=='C' ) {
					j++;

					if(j < cod.length() && cod.charAt(j)=='"' ) {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADORCADENA, j);
						return token;
					}
				}

			}
		}
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para la asignacion de reales de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerAsignacionReal(String cod, int i) {

		int j = 0;
		String lex;
		if(esDigito(cod.charAt(i))) {
			j=i+1;

			while( j < cod.length() && esDigito(cod.charAt(j)) ) {
				j++;
			}

			if(j < cod.length() && cod.charAt(j)=='.' ) {
				j++;
				if(j <cod.length()  && esDigito(cod.charAt(j))) {

					do {
						j++;

					}while ( j < cod.length() && esDigito(cod.charAt(j)) );

					if(j < cod.length() && cod.charAt(j)=='R' ) {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADORREAL, j);
						return token;
					}


				}

			}
		}
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para la asignacion de enteros de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerAsignacionEntero(String cod, int i) {

		int j = 0;
		String lex;
		if(esDigito(cod.charAt(i))) {
			j=i+1;

			while( j < cod.length() && esDigito(cod.charAt(j))) {
				j++;
			}


			if(j < cod.length() && cod.charAt(j)=='E' ) {

				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADORENTERO, j);
				return token;

			}
		}

		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para las sumas de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoSuma(String cod, int i) {
		int j = 0;
		String lex;
		if(cod.charAt(i)=='s') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='u') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='m') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPOSUMA, j);
					return token;
				}
			}
		}	    	
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para las restas de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoResta(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='r') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='e') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='s') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPORESTA, j);
					return token;
				}
			}
		}	    	
		return null;
	}

	/**
	 * Intenta extraer una palabra que califique para las divisiones de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoDivision(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='d') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='i') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='v') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPODIVISION, j);
					return token;
				}
			}
		}	    	
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para las multiplicaciones de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoMultiplicacion(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='m') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='u') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='l') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPOMULTIPLICACION, j);
					return token;
				}
			}
		}	    	
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para los modulos de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoModulo(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='m') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='o') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='d') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPOMODULO, j);
					return token;
				}
			}
		}	    	
		return null;
	}


	/**
	 * Intenta extraer una palabra que califique para nombrar una clase desde cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerNombreClase(String cod, int i) {
		int j = 0;
		String lex;
		if(cod.charAt(i)=='$') {
			j=i+1;
			if(j< cod.length() && esLetra(cod.charAt(j))) {
				do
					j++;
				while (j< cod.length() &&  esLetra(cod.charAt(j)) );

				if(j< cod.length() && esDigito(cod.charAt(j))) {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.CLASE, j);
					return token;

				}
			}
		}

		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para los modificadores de acceso de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoModificadorAcceso(String cod, int i) {
		int j = 0;
		String lex;
		if(cod.charAt(i)=='P') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='u') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='b') {
					j++;
					if(j< cod.length() && cod.charAt(j)=='l') {
						j++;
						if(j< cod.length() && cod.charAt(j)=='i') {
							j++;
							if(j< cod.length() && cod.charAt(j)=='c') {
								j++;
								if(j< cod.length() && cod.charAt(j)=='o') {
									j++;
									lex = cod.substring(i, j);
									Token token = new Token(lex, Token.TIPOMODIFICADORACCESO, j);
									return token;
								}
							}
						}

					}
				}
			}else {
				if(j< cod.length() && cod.charAt(j)=='r') {

					j++;
					if(j< cod.length() && cod.charAt(j)=='i') {
						j++;
						if(j< cod.length() && cod.charAt(j)=='v') {
							j++;
							if(j< cod.length() && cod.charAt(j)=='a') {
								j++;
								if(j< cod.length() && cod.charAt(j)=='d') {
									j++;
									if(j< cod.length() && cod.charAt(j)=='o') {
										j++;
										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.TIPOMODIFICADORACCESO, j);
										return token;
									}
								}
							}

						}
					}
				}
			}	   
		}
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para los caracteres de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoCaracter(String cod, int i) {
		// TODO Auto-generated method stub
		int j = 0;
		String lex;
		if(cod.charAt(i)=='C') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='a') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='r') {
					j++;
					if(j< cod.length() && cod.charAt(j)=='a') {
						j++;
						if(j< cod.length() && cod.charAt(j)=='c') {
							j++;
							if(j< cod.length() && cod.charAt(j)=='t') {
								j++;
								if(j< cod.length() && cod.charAt(j)=='e') {
									j++;
									if(j< cod.length() && cod.charAt(j)=='r') {
										j++;

										lex = cod.substring(i, j);
										Token token = new Token(lex, Token.TIPODATOCARACTER, j);
										return token;

									}
								}
							}
						}

					}
				}
			}
		}	    	
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para las cadenas de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoCadena(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='C') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='a') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='d') {
					j++;
					if(j< cod.length() && cod.charAt(j)=='e') {
						j++;
						if(j< cod.length() && cod.charAt(j)=='n') {
							j++;
							if(j< cod.length() && cod.charAt(j)=='a') {
								j++;
								lex = cod.substring(i, j);
								Token token = new Token(lex, Token.TIPODATOCADENA, j);
								return token;
							}
						}

					}
				}
			}
		}	    	
		return null;

	}
	/**
	 * Intenta extraer una palabra que califique para los enteros de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoEntero(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='E') {

			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='n') {

				j++;
				if(j< cod.length() && cod.charAt(j)=='t') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.TIPODATOENTERO, j);
					return token;
				}
			}
		}	    	
		return null;
	}
	/**
	 * Intenta extraer una palabra que califique para los Reales de cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es una palabra que califique como el nombre de un m�todo. 
	 * 		   El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	private Token extraerTipoReal(String cod, int i) {

		int j = 0;
		String lex;
		if(cod.charAt(i)=='R') {
			System.out.print("entro");
			j=i+1;
			if(j< cod.length() && cod.charAt(j)=='e') {
				System.out.print("entrox2");
				j++;
				if(j< cod.length() && cod.charAt(j)=='a') {
					j++;
					if(j< cod.length() && cod.charAt(j)=='l') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.TIPODATOREAL, j);
						return token;
					}
				}
			}
		}	    	
		return null;
	}


	/**
	 * Intenta extraer un operador relacional de Menor Que de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador menor que. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */

	public Token extraerOperadorMenorQue(String cod, int i)
	{
		if(cod.charAt(i)=='m') { 
			int j=i+1; 
			if(j<cod.length() && cod.charAt(j)=='e') 
			{
				j++;
				if(j<cod.length() && cod.charAt(j)=='n') { 
					j++; 
					String lex = cod.substring(i, j);
					Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j );
					return token; 
				} 
			} 
		}
		return null; 
	}

	/**
	 * Intenta extraer un operador relacional de Mayor Que de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador mayor que
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador mayor que. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerOperadorMayorQue(String cod, int i)
	{
		if(cod.charAt(i)=='m') { 
			int j=i+1; 
			if(j<cod.length() && cod.charAt(j)=='a') { 
				j++;
				if(j<cod.length() && cod.charAt(j)=='y') { 
					j++; 
					String lex = cod.substring(i, j);
					Token token = new Token( lex, Token.OPERADOR_RELACIONAL, j ); 
					return token; 
				} 
			} 
		} return null; 
	}

	/**
	 * Intenta extraer un operador relacional "igual" de la cadena cod a partir de
	 * la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador igual
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador igual. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */

	public Token extraerOperadorIgual(String cod, int i) 
	{ 
		if (cod.charAt(i) == 'i') {
			int j = i + 1; 
			if (j<cod.length() && cod.charAt(j) == 'g') { 
				j++; 
				if (j < cod.length() && cod.charAt(j) == 'u') { 
					j++; 
					String lex = cod.substring(i, j); 
					Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j); 
					return token; 
				}
			} 
		} return null; 
	}

	/**
	 * Intenta extraer un operador relacional "diferente" de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador diferente
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador diferente. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */

	public Token extraerOperadorDiferente(String cod, int i) 
	{
		if (cod.charAt(i) == 'd') {
			int j = i + 1; 
			if (j < cod.length() && cod.charAt(j) == 'i') { 
				j++; 
				if (j < cod.length() && cod.charAt(j) == 'f') {
					j++;
					String lex = cod.substring(i,j); 
					Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j); 
					return token;
				}
			}
		} return null; 
	}

	/**
	 * Intenta extraer un operador relacional "Menor Igual " de la cadena cod a
	 * partir de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador menor igual
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador menor igual. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerOperadorMenorIgual(String cod, int i)
	{
		if (cod.charAt(i) == 'm') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador relacional "Mayor Igual " de la cadena cod a
	 * partir de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador mayor igual
	 * @return el token operador o NULL, si el token en la posici�n dada no es un
	 *         operador mayor igual. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerOperadorMayorIgual(String cod, int i) {
		if (cod.charAt(i) == 'm') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'a') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'y') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador l�gico "Y" de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador "y"
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador "y". El Token se compone de el lexema, el tipo y la posici�n
	 *         del siguiente lexema.
	 */
	public Token extraerOperadorLogicoY(String cod, int i) {
		if (cod.charAt(i) == 'y') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
			return token;
		}
		return null;
	}



	/**
	 * Intenta extraer el operador l�gico negacion de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de negacion
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de negacion. El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerOperadorLogicoNegacion(String cod, int i) {
		if (cod.charAt(i) == 'n') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'g') {
					j++;
					String lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
					return token;
				}
			}
		}
		return null;
	}




	/**
	 * Intenta extraer el operador l�gico "O" de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador "o"
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador "o". El Token se compone de el lexema, el tipo y la posici�n
	 *         del siguiente lexema.
	 */
	public Token extraerOperadorLogicoO(String cod, int i) {
		if (cod.charAt(i) == 'o') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
			return token;
		}
		return null;
	}


	/**
	 * Intenta extraer el operador de asignaci�n de la cadena cod a partir de la
	 * posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de asignacion
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de asignacion. El Token se compone de el lexema, el tipo y
	 *         la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'i') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'g') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'u') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador de suma y asignaci�n de la cadena cod a partir de
	 * la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de suma y asignacion
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de suma y asignacion. El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorSumaAsignacion(String cod, int i) {
		if (cod.charAt(i) == 's') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'm') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador de resta y asignaci�n de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de resta y asignacion
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de resta y asignacion. El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorRestaAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'r') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'e') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 's') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador de multiplicaci�n y asignaci�n de la cadena cod a
	 * partir de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de multiplicaci�n y asignaci�n
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de multiplicaci�n y asignacion. El Token se compone de el
	 *         lexema, el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorMultAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'm') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'u') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'l') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador de divisi�n y asignaci�n de la cadena cod a
	 * partir de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de divisi�n y asignaci�n
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de divisi�n y asignaci�n. El Token se compone de el lexema,
	 *         el tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorDivAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'd') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'i') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'v') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer el operador de m�dulo y asignaci�n de la cadena cod a partir
	 * de la posici�n i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el
	 *            operador de m�dulo y asignaci�n
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         operador de m�dulo y asignaci�n. El Token se compone de el lexema, el
	 *         tipo y la posici�n del siguiente lexema.
	 */
	public Token extraerOperadorModAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'm') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'd') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'g') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'u') {
								j++;
								String lex = cod.substring(i, j);
								Token token = new Token(lex, Token.OPERADOR_ASIGNACION, j);
								return token;
							}
						}
					}

				}
			}
		}
		return null;
	}


	/**
	 * Intenta extraer el s�mbolo de abrir de la cadena cod a partir de la posici�n
	 * i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el s�mbolo
	 *            de abrir
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         s�mbolo de abrir El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerSimboloAbrir(String cod, int i) {
		if (cod.charAt(i) == '<') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_ABRIR, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer el s�mbolo de cerrar de la cadena cod a partir de la posici�n
	 * i, bas�ndose en el Aut�mata
	 * 
	 * @param cod - c�digo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posici�n a partir de la cual se va a intentar extraer el s�mbolo
	 *            de cerrar
	 * @return el token operador o NULL, si el token en la posici�n dada no es el
	 *         s�mbolo de cerrar El Token se compone de el lexema, el tipo y la
	 *         posici�n del siguiente lexema.
	 */
	public Token extraerSimboloCerrar(String cod, int i) {
		if (cod.charAt(i) == '>') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_CERRAR, j);
			return token;
		}
		return null;
	}


	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posici�n i.
	 * Antes de utilizar este m�todo, debe haberse intentado todos los otros m�todos para los otros tipos de token
	 * @param cod - c�digo al cual se le va a extraer el token no reconocido - codigo!=null
	 * @param i - posici�n a partir de la cual se va a extraer el token no reconocido  - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la posici�n del siguiente lexema.

	 */
	public Token extraerNoReconocido ( String cod, int i)
	{
		String lexema =  cod.substring( i, i + 1);
		int j=i+1;
		Token token = new Token( lexema, Token.NORECONOCIDO, j );
		return token;
	}

	/**
	 * Determina si un car�cter es un d�gito
	 * @param caracter - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea un d�gito o no
	 */
	public boolean esDigito (char caracter )
	{
		return  caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un car�cter es una letra may�scula
	 * @param caracter - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra may�scula o no
	 */
	public boolean esMayuscula (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z');
	}

	/**
	 * Determina si un car�cter es una letra min�scula
	 * @param caracter - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra min�scula o no
	 */
	public boolean esMinuscula (char caracter )
	{
		return  (caracter >= 'a' && caracter <= 'z');
	}

	/**
	 * Determina si un car�cter es una letra
	 * @param caracter - Car�cter que se va a analizar - caracter!=null,
	 * @return true o false seg�n el car�cter sea una letra o no
	 */
	public boolean esLetra (char caracter )
	{
		return  (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

}
