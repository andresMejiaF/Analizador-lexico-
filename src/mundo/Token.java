/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindío (Armenia - Colombia)
 * Programa de Ingeniería de Sistemas y Computación
 *
 * Asignatura: Teoría de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Diseño original por: Leonardo A. Hernández R. - Agosto 2008 - Marzo 2009
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
    final public static String OPERADOR_LOGICO = "Palabra usada para identificar un operador Lógico";
    final public static String OPERADOR_ASIGNACION = "Palabra usada para identificar un operador de Asignación";
    final public static String SIMBOLO_ABRIR = "Palabra usada para identificar el símbolo de abrir";
    final public static String SIMBOLO_CERRAR = "Palabra usada para identificar el símbolo de cerrar";
    final public static String TIPODATOENTERO = "Palabra para los enteros";
    final public static String TIPODATOREAL = "Palabra para los reales";
    final public static String TIPODATOCADENA = "Palabra para las cadenas";
    final public static String TIPODATOCARACTER = "palabra para los caracteres";
    final public static String TIPOMODIFICADORACCESO = "palabra para los modificadores de acceso";
    final public static String CLASE = "palabra para las clases";
    final public static String TIPOSUMA = "Palabra para la suma";
    final public static String TIPORESTA = "Palabra para la resta";
    final public static String TIPOMULTIPLICACION = "Palabra para la multiplicación";
    final public static String TIPODIVISION = "Palabra para la división";
    final public static String TIPOMODULO = "Palabra para el módulo";
    final public static String OPERADORENTERO = "Palabra para la asignacion de enteros";
    final public static String OPERADORREAL = "Palabra para la asignacion de reales";
    final public static String OPERADORCADENA = "Palabra para la asignacion para las cadenas";
    final public static String OPERADORCARACTER = "Palabra para la asignacion para los caracteres";
    // Lopez Juan Jose
    final public static String SEPARADORSENTENCIAS = "Palabra usada para separar sentencias";
    final public static String BUCLE = "Palabra para iniciar un bucle o ciclo";
    final public static String DECISION = "Palabra para iniciar una decisión";
    final public static String CLASETIPO = "Palabra para la determinación de una clase";
    final public static String VARIABLES = "Palabra funcional como variable";
    final public static String METODOS = "Palabra funcional como método";
    final public static String INICIAL = "Palabra para inicar una línea";
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
     * posición del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posición del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Entrega la información del token
     * @return Descripción del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     Índice del siguiente: " + indiceSiguiente;
    }

    /**
     * Método que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * Método que retorna la posición del siguiente lexema
     * @return posición del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * Método que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }




}
