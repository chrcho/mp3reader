# MP3-Reader

Durchsucht ein Verzeichnis nach MP3-Dateien. Eine Filterung nach Genre ist möglich. Das Projekt ist in Java implementiert. Es werden keine Bibliotheken benutzt.

## Verwendete Software:

 * Eclipse Oxygen (4.7.0) mit Embedded Maven (3.3.9)
 * JDK 8u131

## Anleitung (Projekt in Eclipse importieren und ausführen):

 * Eclipse starten
 * Unter "Window > Preferences" nach "Environment" filtern und eine JDK 8 für JavaSE-1.8 auswählen
 * Rechtsklick im Package Explorer unter "Import > nach Maven filtern > Existing Maven Projects
   > Browse to project > Select project > Finish"
 * Rechtsklick auf das Projekt danach "Run As > Java Application" betätigen (Argumente fehlen)
 * Run Configuration vom Projekt öffnen und Programm-Argumente eingeben (ein Verzeichnis und optional 
   ein Genre-Filter)
   
*Hinweis*: Wenn der Pfad zum Verzeichnis oder der Genre-Filter Leerzeichen enthält, muss der String 
           in Anführungszeichen gesetzt werden!
 
## Anleitung (mp3reader.jar ausführen):

 * Shift halten und Rechtsklick auf Projekt-Verzeichnis
 * Eingabeaufforderung hier öffnen
 * D:\...\mp3reader> java -jar mp3reader.jar "D:\Musik"
   oder 
 * D:\...\mp3reader> java -jar mp3reader.jar "D:\Musik" "Pop"
 