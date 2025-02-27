
# Bloc 1. Activitats sobre el comptador

## Anàlisi de l'estructura d'un projecte

### Tipus de projecte

Les aplicacions Android no tenen una única funció principal/punt d'entrada, sinó que poden tindre diferents components/funcions també conegudes com a activitats. En crear un nou projecte, Android Studio permet triar entre una sèrie de plantilles per a diferents dispositius.

En el projecte del comptador s'ha utilitzat una plantilla per a tauletes i mòbils. Per al comptador inicial i el que utilitza MVVM probablement s'utilitzà la plantilla *Empty Views Activity*, mentre que el comptador dissenyat amb *Jetpack Compose* potser fou creat amb la plantilla *Empty Activity*.

En resum, les plantilles ens generen l'estructura d'un projecte amb una activitat amb vistes buida. En crear un projecte, se'ns demana el llenguatge de programació, el SDK mínim i el llenguatge amb què especifiquem el fitxer de configuració. A les últimes versions es permet fer ús d'un DSL basat directament en Kotlin.

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_1.jpg" alt="Imatge 2" width="300"/>
</p>


### Estructura del projecte

Android utilitza Gradle com a ferramenta de construcció de projectes. Els projectes s'organitzen en mòduls, cada mòdul és una aplicació.
Des de la vista dels fitxers del projecte *(Project Files)*, veiem l'estructura que es genera en un projecte Android.
<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_2.jpg" alt="Imatge 1" width="400"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_3.jpg" alt="Imatge 2" width="400"/>
</p>

### Fitxers i carpetes més importants

#### En el Projecte

El projecte té l'estructura d'un projecte Gradle. Amb el directori del mòdul **app** o el directori amb fitxers de configuració **gradle** .
Veguem que de la carpeta arrel penja un script de configuració: `build.grade.kts` en format Kotlin DSL que tindrá la configuració comuna a tots els mòduls del projecte.

El `build.grade.kts` té alies als plugins que utilitza l'aplicació, estos plugins están definits en el fitxer `libs.versions.toml` que està a dins del directori **gradle**.

Contingut de `build.grade.kts`:

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

```
<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_4.jpg" alt="Imatge 1" width="400"/>
</p>

#### Dins del mòdul/aplicació

El mòdul també té un script de construcció propi `build.grade.kts`, conté els plugins que utilitzarà l'aplicació definits al script general.
A l'inici veguem l'espai de nom de l'aplicació (com.pmdm.ieseljust.comptador) i el sdk que utilitza (34).
Veguem els apartats:

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_5.jpg" alt="Imatge 5" width="400"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_6.jpg" alt="Imatge 6" width="400"/>
</p>

### Estructura d'una activitat i quins fitxers están implicats

#### Què és una activitat?

Les activitats són les **diferents pantalles** d'una aplicació Android on recollim la interacció de l'usuari. Aquesta interacció provoca **canvis d'estat** en 
les activitats. Per tant, les activitats tenen canvis d'estat durant el seu **cicle de vida**.

#### Com s'organitzen?

Les activitats són objectes de la classe **Activity**, hereten d' **AppCompatActivity**, i són instanciades pel propi sistema Android.

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_7.jpg" alt="Imatge 5" width="300"/>
</p>

En Jetpack Compose, les interfícies d'usuari es defineixen directament en codi Kotlin, sense fitxers XML de Layout.

#### La classe MainActivity i el seu fitxer Layout

La plantilla Empty Views Activity genera una classe principal MainActivity i la seua vista/Layout XML:

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_9.jpg" alt="Imatge 9" width="800"/>
</p>

## Modificacions inicials

### Reemplaçar el layout del comptador

El layout que utilitzem per al projecte del comptador inicial i el de disseny MVVM utiliza un tipus de vista `TextView`, aquesta tindrà un atribut identificador per a poder referir-nos a ella desde el codi: `@+id/textViewComptador`
En ella anirà el text del número del comptador. També tenim un botó `Button` amb identificador `@+id/btAdd`. Hem d'afegir els botons de *decrementar*, *ressetejar* i *obrir*.

Veguem que els elements del xml, tant el textView com els botons tenen definides unes propietats. Veguem entre estes propietats les constraints dels botons:
defineixen com es col·loquen i s'alineen aquests en la pantalla. Marquen com es relaciona cada botó o 'vista' amb altres vistes o
amb el "pare" (l'area on es dibuixa tot). Cada vista dins d'un ConstraintLayout necessita almenys **dues** constraints **horitzontals** i dues constraints **verticals**.

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_10.jpg" alt="Imatge 10" width="900"/>
</p>

**En Jetpack Compose no s'utilitza Layout.**

### Afegir el codi a l'activitat principal per dotar de funcionalitat  els botons de decrementar i ressetejar

#### En el comptador inicial

En el main tractarem la interacció del usuari associant una acció a un esdeveniment en la interfície:

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_11.jpg" alt="Imatge 11" width="600"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_12.jpg" alt="Imatge 12" width="600"/>
</p>




## Activitats sobre el cicle de vida i la pèrdua d'estats

### Fases del cicle de vida de l'activitat principal i ús de mètodes de la classe Log

Les activitats, al llarg de la seua vida útil, passen per diferents estats. En aquestes transicions entre estats es disparen certs esdeveniments, que podem capturar 
i gestionar mitjançant funcions de callback. Incorporaré el mètode `Log.i(String, String)` de la classe **Log** per obtindre informació el funcionament d'aquestos esdeveniments de vida al fitxer **Logcat**:

1. `onCreate():` Este callback es dispara quan es crea una activitat, amb ell **inicialitzem els components** d'una activitat i enllaçem les vistes vinculades, en aquest
mètode és on generalment utilitzem `setContentView()`.
    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_16.jpg" alt="Imatge 16" width="900"/>
    </p>
2. `onStart():` Este callback fa que l'activitat siga **visible per a l'usuari**. Una vegada finalitzat este callback, l'activitat ingressa al onResume().
    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_17.jpg" alt="Imatge 17" width="900"/>
    </p>

4. `onResume():` Quan l'activitat passa a primer pla, ja siga perquè s'acaba de crear o ha sigut reanudada el sistema invoca onResume(),
    podriem dir que és el mètode que s'usa quan l'activitat està en **primer pla i espera la interacció de l'usuari**. L'activitat es manté en
    este estat fins que li passa alguna cosa com aillar el focus de l'app, quan el dispositiu rep una trucada, etc. A més, onResume() inicialitza
    tots els components que es llacen amb onPause().
      <p align="center">
           <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_20.jpg" alt="Imatge 20" width="900"/>
      </p>

5. `onPause():` Quan es produeix un **esdeveniment disruptiu**, com els comentats anteriorment, l'activitat entra en l'estat de pausa. I el sistema invoca
onPause().
   <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_18.jpg" alt="Imatge 18" width="900"/>
   </p>

6. `onStop():` S'invoca quan l'activitat **ja no és visible** per a l'usuari.

    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_19.jpg" alt="Imatge 19" width="900"/>
   </p>
7. `onRestart():` S'invoca quan una activitat que estava detinguda passa de nou a estar en primer pla. **Restaura l'estat** en el que estava l'activitat en el moment que aquesta es va detindre.
    Després d'aquest callback sempre s'invoca a onStart().

    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_21.jpg" alt="Imatge 21" width="900"/>
   </p>
8. `onDestroy():` Abans que s'elimine l'activitat s'invoca a aquest callback per a assegurar-se de que **s'alliberen els recursos i el procés** de l'activitat.

    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_22.jpg" alt="Imatge 22" width="900"/>
    </p>

#### Guardar l'estat de les activitats amb objectes de tipus Bundle

Algunes accions de l'usuari, a més de parar l'activitat provoquen que aquesta es destruïsca: Per què quan canviem l'orientació del dispostiu es destrueix i es recrea l'activitat?
**Problema:**    
    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_25.jpg" alt="Imatge 25" width="900"/>
    </p>
Açò passa al aplicar els nous recursos de disposició adequats (com ara layout en mode horitzontal o vertical). Per això es crida al mètode `onDestroy()`, seguit de `onCreate()` per crear una nova instància de l'activitat.
Per a conservar el valor del comptador quan es produeix aquest canvi, utilitzem els mètodes `onSaveInstanceState` i `onRestoreInstanceState`. Estos hereten d'**Activity**:

- **onSaveInstanceState** : S'invoca **abans de destruir l'activitat.**
    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_23.jpg" alt="Imatge 23" width="900"/>
    </p>

- **onRestoreInstanceState** : S'invoca **abans de retornar-la a primer pla.**
    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_24.jpg" alt="Imatge 24" width="900"/>
    </p>

Per a guardar l'estat d'una activitat s'utilitza un objecte de tipus **Bundle** que emmagatzema parells de **clau-valor**
**Mètodes de Bundle**:
- putInt/getInt
- putString/getString

## Intents entre activitats 

Mentre que els mètodes `onSaveInstanceState()` i `onRestoreInstanceState()` estan dissenyats per preservar l'estat d'una activitat quan es produeixen canvis com el canvi d'orientació (dins d'una **mateixa activitat**);
les intents (**`Intents`**) són missatges asíncrons utilitzats principalment per passar informació entre **diferents activitats** o components dins d'una aplicació.

### Creació d'una nova activitat per a treballar amb intents: MostraComptadorActivity

El que volem fer és crear una nova activitat per tal de pasar-li l'estat del comptador i que esta la mostre utilitzant intents. Per a això farem els seguents passos:
1. Crearem una nova activitat **Empty Views Activity**. És important **no marcar** que és una activitat amb llançador:

    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_26.jpg" alt="Imatge 26" width="900"/>
    </p>
   Al crear l'activitat, es crearan els fitxers corresponents: el seu .kt i el layout a /res .xml. A més s'afegirà la nova activitat al fitxer AndroidManifest.xml.
   Una vegada creada, modificarem el seu layout:

    <p align="center">
        <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_27.jpg" alt="Imatge 27" width="600"/>
    </p>
    Com veguem, tindrà dos vistes: un TextView i un Button.
   
2. Incorporar codi a les activitats:
   + En **MainActivity**:
<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_28.jpg" alt="Imatge 28" width="800"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_29.jpg" alt="Imatge 29" width="800"/>
</p>

Açò obrirà la finestra de la nova activitat, però no vorem el valor del comptador actualitzat, farà falta assignar-li el valor amb els següents passos:

+ En **MostraComptadorActivity**:
<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_30.jpg" alt="Imatge 30" width="800"/>
</p>

# Bloc 2. Comptador amb MVVM.


El patró Model-Vista-ViewModel (o MVVM), és un patró d'arquitectura basat en fluxos observables, que segueix els principis i l'arquitectura d'aplicació proposada per Google, tractant-se així del patró proposat per a estructurar les aplicacions en Android.
És divideix en tres components:
-   Model de dades: Obté les dades de qualsevol font de dades i les exposa als ViewModels.
-   Vista: Gestiona la interfície d'usuari.
-   ViewModel: Situat entre les dos anteriors, té controladors d'esdeveniments que les vistes poden observar i així ser actualitzades.

De esta manera, per a adaptar el comptador al disseny MVVM, el primer que farem serà:
1. En primer lloc, definirem la classe ``ViewModel``, amb els LiveData necessaris.

2. En segon lloc, actualitzarem les activitats per a observar aquests LiveData i actualitzar la interficie d'usuai en conseqüència.

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_13.jpg" alt="Imatge 13" width="400"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_14.jpg" alt="Imatge 14" width="400"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_15.jpg" alt="Imatge 14" width="400"/>
</p>

## Amb l'arquitectura MVVM, les Intents seguirien sent necessaries?
Aquest model, resol el problema de pèrdua d'estats, que es produeix quan Android decideix alliberar memòria destruint activitats
o fragments, o quan es produeix una rotació de pantalla, ja que és el ViewModel, qui manté aquest estat.
L'interessant ací és que els ViewModels no es destrueixen, de manera que quan una activitat o fragment eliminats per falta de memòria o canvis de configuració
es torna a crear, pot vincular-se de nou als LiveData, mantenint així l'estat: Es pot guardar el comptador en una capa inferior i fer dos viewmodels, un per cada vista.
Així que tampoc faría falta utilitzar Intents com hem fet en este cas.

# Bloc 3. Comptador amb Compose.
Jetpack Compose utilitza funcions composables, és a dir, Es tracta de funcions que descriuen una part de la interfície d'usuari. Per descriure una funció com a Composable fem ús de l'anotació @Composable.
Compose gestiona l'estat de manera reactiva, de manera que quan aquest estat canvia, es recomposen les parts de la intefície que depenen de l'estat.
Crearem un projecte dissenyat amb Jetpack Compose elegint la plantilla **Empty Activity**
Veurem que no utilitzarem un fitxer XML per al layout ja que en este disseny no s'utilitza. Per tant, no existirà la carpeta ``res/layout``

### Elements bàsics del codi:

<p align="center">
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_31.jpg" alt="Imatge 31" width="800"/>
  <img src="https://github.com/SofiaGracia/Gracia_Mascarell_Sofia_PMDM2425/blob/main/Imatges/Screenshot_32.jpg" alt="Imatge 32" width="800"/>
</p>