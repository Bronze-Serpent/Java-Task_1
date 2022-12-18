package barabanov.ui.servlets.item;

import barabanov.ORM.DAOItemJDBC;
import barabanov.entity.IDToken;
import barabanov.entity.Item;
import barabanov.service.ItemService;
import barabanov.ui.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "itemCreateServlet", value = "/itemCreateServlet")
public class itemCreateServlet extends HttpServlet
{

    private final String itemPage = "item.jsp";

    private ItemService itemS;


    @Override
    public void init() throws ServletException
    {
        super.init();

        Connection dbConnection = DBManager.getManager().getConn();
        itemS = new ItemService(new DAOItemJDBC(dbConnection));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long id = Long.parseLong(request.getParameter("id"));
        long playerId = Long.parseLong(request.getParameter("playerId"));
        long resourceId = Long.parseLong(request.getParameter("resourceId"));
        long count = Long.parseLong(request.getParameter("count"));
        long level = Long.parseLong(request.getParameter("level"));

        Item item = new Item(new IDToken(id, playerId, resourceId), count, level);

        try {
            itemS.writeToDB(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher(itemPage).forward(request, response);
    }
}
