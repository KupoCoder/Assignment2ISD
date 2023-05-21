@WebServlet("/shipments")
public class ShipmentServlet extends HttpServlet {
    private ShipmentDAO shipmentDAO = new ShipmentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post logic here
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get logic here
    }
}
