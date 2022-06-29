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

/**
 * Clase que modela un token
 */

public class Token {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constantes para modelar los posibles tipos de token que se van a analizar
     */
	
	final public static String OPERADOR_RELACIONAL = "Palabra usada para identificar un operador relacional";
    final public static String OPERADOR_LOGICO = "Palabra usada para identificar un operador L�gico";
    final public static String OPERADOR_ASIGNACION = "Palabra usada para identificar un operador de Asignaci�n";
    final public static String SIMBOLO_ABRIR = "Palabra usada para identificar el s�mbolo de abrir";
    final public static String SIMBOLO_CERRAR = "Palabra usada para identificar el s�mbolo de cerrar";
    final public static String TIPODATOENTERO = "Palabra para los enteros";
    final public static String TIPODATOREAL = "Palabra para los reales";
    final public static String TIPODATOCADENA = "Palabra para las cadenas";
    final public static String TIPODATOCARACTER = "palabra para los caracteres";
    final public static String TIPOMODIFICADORACCESO = "palabra para los modificadores de acceso";
    final public static String CLASE = "palabra para las clases";
    final public static String TIPOSUMA = "Palabra para la suma";
    final public static String TIPORESTA = "Palabra para la resta";
    final public static String TIPOMULTIPLICACION = "Palabra para la multiplicaci�n";
    final public static String TIPODIVISION = "Palabra para la divisi�n";
    final public static String TIPOMODULO = "Palabra para el m�dulo";
    final public static String OPERADORENTERO = "Palabra para la asignacion de enteros";
    final public static String OPERADORREAL = "Palabra para la asignacion de reales";
    final public static String OPERADORCADENA = "Palabra para la asignacion para las cadenas";
    final public static String OPERADORCARACTER = "Palabra para la asignacion para los caracteres";
    // Lopez Juan Jose
    final public static String SEPARADORSENTENCIAS = "Palabra usada para separar sentencias";
    final public static String BUCLE = "Palabra para iniciar un bucle o ciclo";
    final public static String DECISION = "Palabra para iniciar una decisi�n";
    final public static String CLASETIPO = "Palabra para la determinaci�n de una clase";
    final public static String VARIABLES = "Palabra funcional como variable";
    final public static String METODOS = "Palabra funcional como m�todo";
    final public static String INICIAL = "Palabra para inicar una l�nea";
    //
    final public static String NORECONOCIDO = "PALABRA NO RECONOCIDA";
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lexema
     */
    private String lexema;

    /**
     * tipo
     */
    private String tipo;

    /**
     * posici�n del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posici�n del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Entrega la informaci�n del token
     * @return Descripci�n del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     �ndice del siguiente: " + indiceSiguiente;
    }

    /**
     * M�todo que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * M�todo que retorna la posici�n del siguiente lexema
     * @return posici�n del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * M�todo que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }




}
