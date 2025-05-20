package ProyectoFinalProgramacion;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializablePersona p1 = new SerializablePersona("Perita");
        File file = new File("C:\\Users\\1DAW2425-02\\persona.bin");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p1);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C:\\Users\\1DAW2425-02\\EjecucionesProyecto\\persona.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializablePersona p1Deserializado = (SerializablePersona) ois.readObject();

        ois.close();
        fis.close();

        System.out.println(p1Deserializado.nombre);
    }
}
