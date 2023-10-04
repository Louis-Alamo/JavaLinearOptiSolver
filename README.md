## Actividad Extra de Revisión 2023

Este repositorio contiene un proyecto desarrollado en **Java** como parte de una actividad extra de revisión. El objetivo principal del proyecto es resolver problemas de programación lineal para casos de maximización mediante los métodos **Simplex** y **Gráfico** aprendidos en clase.

### Detalles del Proyecto
- **Lenguaje de Programación:** Java
- **Entornos de Desarrollo:** Netbeans e IntelliJ

### Funcionalidades del Programa
El programa permite a los usuarios:

- Resolver problemas de programación lineal con hasta **4 restricciones** y **2 variables**.
- Utilizar dos métodos: **Simplex** y **Gráfico**.
- Ingresar la función objetivo y las restricciones a través de una interfaz de usuario **GUI**.

### Método Gráfico
- Interfaz basada en **javax.swing** con representación visual del problema.
- **Escala automática** en función del rango de coordenadas.
- Representación gráfica de las restricciones y área de solución.
- Detalles visuales de intersecciones y puntos óptimos.

### Método Simplex
- Interfaz basada en **javax.swing** utilizando componentes **JTable** para la tabla simplex.
- Adaptable según la cantidad de restricciones especificadas.
- Identificación automática de variables no utilizadas.
- Indicación de valores de variables y maximización total.

### Limitaciones del Programa
**Método Gráfico:**
- Escala gráfica puede presentar problemas con valores muy grandes.
- No representa coordenadas negativas visualmente, pero los cálculos son precisos.
- Falta de visualización de puntos de intersección en el gráfico.

**Método Simplex:**
- Falta de títulos para las filas en la tabla.
- Muestra valores no redondeados, lo que puede resultar en decimales precisos en los resultados.

Este proyecto es una herramienta educativa que proporciona una comprensión práctica de los métodos **Simplex** y **Gráfico** para problemas de programación lineal. Se recomienda a los usuarios tener en cuenta las limitaciones mencionadas durante su utilización. ¡Gracias por revisar nuestro proyecto!
