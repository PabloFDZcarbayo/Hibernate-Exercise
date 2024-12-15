package DAO;

import Entities.Atracciones;
import Entities.Tikects;
import Entities.Usuarios;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.example.Main.session;

public class TikectsDAO {

    private static Session session = null;

    public TikectsDAO() {
        session = HibernateUtil.getSession();
    }



    public static void crearTikects(Usuarios user, Atracciones atraccion) {
        Transaction transaction = session.beginTransaction();
        Tikects tikect = new Tikects();
        tikect.setUsuario(user);
        tikect.setNombreAtraccion(atraccion);
        tikect.setPrecioEntrada(atraccion.getPrecio());
        session.persist(tikect);
        transaction.commit();
        System.out.println("Tikect insertado correctamente.");


    }

    public static void eliminarTikect(int idTikect) {
        Transaction transaction = session.beginTransaction();
        Tikects tikect = session.get(Tikects.class, idTikect);
        session.delete(tikect);
        transaction.commit();
        System.out.println("Tikect eliminado correctamente.");
}

public static void BuscarTikect(int idTikect) {
    try {
        Tikects tikect = session.get(Tikects.class, idTikect);
        if (tikect != null) {
            System.out.println("Tikect encontrado: ");
            System.out.println("-->");
            System.out.println("ID: " + tikect.getId());
            System.out.println("Usuario: " + tikect.getUsuario().getNombre());
            System.out.println("Atraccion: " + tikect.getNombreAtraccion().getNombre());
            System.out.println("Precio: " + tikect.getPrecioEntrada());
            System.out.println("--------");
        }else {
            System.out.println("El tikect no existe.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
