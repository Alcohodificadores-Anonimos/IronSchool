# IronSchool

##  INTRODUCCIÓN
IronSchool es un proyecto desarrollado por el grupo **Alcohodificadores-Anónimos&reg;** desarrollado en el lenguaje de programación Java el cual simula una gestión de venta de cursos. Dicho proyecto forma parte los proyectos grupales que Ironhack&copy; formula a los alumnos de sus bootcamps para que estos desarrollen el proyecto de forma grupal.

##  INSTRUCCIONES DE INSTALACIÓN DEL PROGRAMA PARA SU USO

**1.** El primer paso que deberemos seguir es descargarnos la carpeta que contiene todo el proyecto y a continuación, la descomprimimos en la ubicación que nosotros escojamos.

**2.** Una vez descargado y descomprimido el programa deberemos iniciar nuestro IDE para ejecutar el programa JAVA, buscaremos dentro del IDE la opción para abrir un archivo ya existente. Una vez abierta la carpeta de nuestro programa desde el IDE, dentro de la carpeta del proyecto, buscaremos la carpeta cuyo nombre es "src" y dentro de esta localizaremos la carpeta main que contiene una carpeta nombrada java y ahi localizaremos los archivos .java para correr el programa.

Ejemplo dirección de directorios: ../IronSchool/.idea/src/main/java/ironschool

**3.** A continuación, tendremos todo listo para correr nuestro programa, deberemos acceder al archivo cuyo nombre es "Main" y ejecturar dicho archivo desde la IDE para que corra el programa.

##  INSTRUCCIONES DEL PROGRAMA PARA SU USO
### 1.- Menú creación Escuela y Profesorado 

[![Image from Gyazo](https://i.gyazo.com/2eee02016d6dcd9e64a49fa041c370d8.png)](https://gyazo.com/2eee02016d6dcd9e64a49fa041c370d8)

    1.- El programa nos pedirá que introduzcamos el nombre de nuestra escuela.
    2.- El programa nos preguntará cuantos profesores queremos añadir a nuestra escuela.
    3.- Introduciremos el nombre del profesor 1 y posteriormente su salario y así consecutivamente con el resto del profesorado.

### 2.- Menú creación Cursos

[![Image from Gyazo](https://i.gyazo.com/fafbbe292c7f46f1e53e20349de47c95.png)](https://gyazo.com/fafbbe292c7f46f1e53e20349de47c95)

    1.- El programa nos pedirá que introduzcamos el total de cursos.
    2.- Escogeremos el nombre del primer curso que ofrezcamos y su precio.
    3.- Repetiremos el punto 2 y así consecutivamente con el resto de cursos.
    
### 3.- Menú creación Estudiantes

[![Image from Gyazo](https://i.gyazo.com/12fe1f06401bda768574577fb635bf68.png)](https://gyazo.com/12fe1f06401bda768574577fb635bf68)

    1.- Introduciremos el numero de estudiantes que queremos añadir a nuestro programa.
    2.- Introduciremos los siguientes datos por alumno: nombre, dirección y e-mail.

### 4.- Menú ejecución de comandos e interacción con el programa

[![Image from Gyazo](https://i.gyazo.com/7501c6a709d0014481138c88d8527d26.png)](https://gyazo.com/7501c6a709d0014481138c88d8527d26)
   
    
**EJEMPLOS:**
#### ENROLL
[![Image from Gyazo](https://i.gyazo.com/9cc79b0b79621f3e3f7a9d31b33eee27.png)](https://gyazo.com/9cc79b0b79621f3e3f7a9d31b33eee27)
 
    Hemos añadido al alumno con ID:1 (Hermione Granger) al curso con ID:0 (Defense Against the Dark Arts)
    
 [![Image from Gyazo](https://i.gyazo.com/f5f6dbdd121bf1b2dc0051c6778a6a09.png)](https://gyazo.com/f5f6dbdd121bf1b2dc0051c6778a6a09)
 
    Solo podremos asignar un curso a un estudiante, por lo tanto, cuando queramos asignar un curso a un estudiante el cual ya esté asignado a un curso nos aparecerá dicho mensaje.

#### ASSIGN

[![Image from Gyazo](https://i.gyazo.com/398aaacd97c2db987ad54f3b77e3ecc0.png)](https://gyazo.com/398aaacd97c2db987ad54f3b77e3ecc0)
    
    Hemos añadido el profesor con ID:0 (Albus Dumbledore) al curso con ID:0 (Defense Against the Dark Arts).
    
*En el caso de que queramos introducir un nuevo profesor a un curso el cual ya tiene un profesor asignado, el antiguo profesor será sobreescrito por el nuevo*
    
#### SHOW COURSES 

[![Image from Gyazo](https://i.gyazo.com/ff21e7725df4543819b2cca876ca306d.png)](https://gyazo.com/ff21e7725df4543819b2cca876ca306d)
    Con el comando SHOW COURSES podremos obtener información relativa a los cursos que hemos creado.

#### LOOKUP COURSE <ID_COURSE>

[![Image from Gyazo](https://i.gyazo.com/607c1bc155f5ba5e9de1954323c6a4d6.png)](https://gyazo.com/607c1bc155f5ba5e9de1954323c6a4d6)

    LOOKUP COURSE nos muestra toda la información relativa a un curso en concreto.

 #### SHOW PROFIT
 
 [![Image from Gyazo](https://i.gyazo.com/d0a5774774a4c55731be0c223ca2f7bf.png)](https://gyazo.com/d0a5774774a4c55731be0c223ca2f7bf)

*Hay que remarcar que en este caso el beneficio es negativo debido a que no ha sido asignado ningún alumno a un curso debido a que están de vacaciones y no se han matriculado aún, solo se han tenido en cuenta el sueldo de los profesores.*


 ## DIAGRAMA DE CLASE

[![Image from Gyazo](https://i.gyazo.com/ded2a6a022b8ef0ea8291525df75d298.png)](https://gyazo.com/ded2a6a022b8ef0ea8291525df75d298)

## CRÉDITOS
Organización: Alcohodificadores-Anónimos® 2022

Raul Ruiz: https://github.com/RaulRuiz1997    LinkedIn: https://www.linkedin.com/in/ra%C3%BAl-ruiz-pedrosa-3b4135164 \
Eduard Blanco: https://github.com/eblancode   LinkedIn: https://www.linkedin.com/eduardblanco \
Manuel Orfe: https://github.com/Manuelorfe    LinkedIn: https://www.linkedin.com/in/manuel-ortega-fernandez \
Xavi Romero: https://github.com/xavi-dig      LinkedIn: https://www.linkedin.com/in/xaviromerolopez \
Cristian Pérez: https://github.com/KogeCode   LinkedIn: https://www.linkedin.com/in/cristian-p%C3%A9rez-barbero-87616478/
