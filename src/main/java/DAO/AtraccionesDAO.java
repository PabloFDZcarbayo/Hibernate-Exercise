package DAO;

import Entities.Atracciones;
import Entities.Tikects;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class AtraccionesDAO {
    private static Session session = null;

    public AtraccionesDAO() {
        session = HibernateUtil.getSession();
    }

    public static void CrearAtraccion(Atracciones atraccion) {
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(atraccion);
            transaction.commit();
            System.out.println("Atracción insertada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void BuscarAtraccion(int idAtraccion) {
        try {
            Atracciones atraccion = session.get(Atracciones.class, idAtraccion);
            if (atraccion != null) {
                System.out.println("Atracción encontrada: ");
                System.out.println("-->");
                System.out.println("ID: " + atraccion.getId());
                System.out.println("Nombre: " + atraccion.getNombre());
                System.out.println("Precio: " + atraccion.getPrecio());
                System.out.println("--------");
            }else {
                System.out.println("La atraccion no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void EliminarAtraccion(int idAtraccion) {
        try {
            Atracciones atraccion = session.get(Atracciones.class, idAtraccion);
            if (atraccion != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(atraccion);
                transaction.commit();
                System.out.println("Atracción eliminada correctamente.");
            }else {
                System.out.println("La atraccion no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ActualizarAtraccion(int idAtraccion, String nombreAtraccion, BigDecimal precio) {
        try {
            Atracciones atraccion = session.get(Atracciones.class, idAtraccion); //Recupra la atracción con los parametros pasados
            if (atraccion != null) {
                Transaction transaction = session.beginTransaction();
                atraccion.setNombre(nombreAtraccion);
                atraccion.setPrecio(precio);
                session.update(atraccion); //Actualiza la atracción
                transaction.commit();
                System.out.println("Atracción actualizada correctamente.");
            }else {
                System.out.println("La atraccion no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void NumeroEntradas(String nombreAtraccion) {
        try {
               Long entradas = (long) session.createQuery("SELECT COUNT(t) " + //Cuenta el número de filas que tengan el id de la atraccion
                       "FROM Tikects t " +
                       " WHERE t.nombreAtraccion.nombre = :nombreAtraccion" ).setParameter("nombreAtraccion", nombreAtraccion).uniqueResult(); //Devuelve el número de filas como unico resultado ya que solo queremos el conteo

               System.out.println("El numero de entradas que ha vendido la atraccion " + nombreAtraccion + " es: " + entradas);
               System.out.println("--------");

               if (entradas > 0) {
                   // Obtener y mostrar los detalles de los tickets vendidos
                   List<Tikects> tikects = session.createQuery( //Crea una consulta que selecciona todos los tikects y los filtra por el nombre de la atraccion
                                   "FROM Tikects t " +
                                           "WHERE t.nombreAtraccion.nombre = :nombreAtraccion", Tikects.class)
                           .setParameter("nombreAtraccion", nombreAtraccion)
                           .getResultList(); //Devuelve una lista de tikects

                   System.out.println("Detalles de los tickets vendidos para la atracción '" + nombreAtraccion + "':");
                   System.out.println("--------");
                   for (Tikects tikect : tikects) { //Itera sobre la lista de tikects e imprime sus detalles
                       System.out.println("Id Tikect: " + tikect.getId());
                       System.out.println("Usuario: " + tikect.getUsuario().getNombre());
                       System.out.println("Atraccion: " + tikect.getNombreAtraccion().getNombre());
                       System.out.println("Precio: " + tikect.getPrecioEntrada());
                       System.out.println("--------");
                   }
               }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
