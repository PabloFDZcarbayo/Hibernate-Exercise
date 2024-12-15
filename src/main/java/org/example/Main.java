package org.example;

import DAO.AtraccionesDAO;
import DAO.TikectsDAO;
import DAO.UsuariosDAO;
import Entities.Atracciones;

import Entities.Tikects;
import Entities.Usuarios;
import org.hibernate.Session;

import java.math.BigDecimal;

public class Main {
    public static Session session = null;

    public static void main(String[] args) {
        TikectsDAO tikectsDAO = new TikectsDAO();
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        AtraccionesDAO atraccionesDAO = new AtraccionesDAO();

        Usuarios Peter = new Usuarios();
        Peter.setNombre("Peter");


        Atracciones atraccion = new Atracciones();
        atraccion.setNombre("El abismo");
        atraccion.setPrecio(BigDecimal.valueOf(60.76));



        try {
            session = HibernateUtil.getSession();
            usuariosDAO.crearUsuario(Peter);
           //usuariosDAO.BuscarUsuario(3);
           //usuariosDAO.eliminarUsuario(7);
            //usuariosDAO.actualizarNombreUsuario(4,"Pepito");

           // atraccionesDAO.CrearAtraccion(atraccion);
           // atraccionesDAO.BuscarAtraccion(10);
           // atraccionesDAO.EliminarAtraccion(3);
           // atraccionesDAO.ActualizarAtraccion(1,"Dragon Khan",BigDecimal.valueOf(47.12));

            Usuarios user = session.get(Usuarios.class, 4);
            Atracciones elAbismo = session.get(Atracciones.class, 6);

           // tikectsDAO.crearTikects(user,atraccion);
            // tikectsDAO.BuscarTikect(4);
           // tikectsDAO.eliminarTikect(4);
           // UsuariosDAO.gastoMedioPorUsuario();
            //AtraccionesDAO.NumeroEntradas("Dragon Khan");




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }

    }



    }
