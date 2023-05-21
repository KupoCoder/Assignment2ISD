public class ShipmentDAO {
    private static Map<Integer, Shipment> database = new HashMap<>();

    public void create(Shipment shipment) {
        database.put(shipment.getShipmentId(), shipment);
    }

    public Shipment read(int shipmentId) {
        return database.get(shipmentId);
    }

    public void update(Shipment shipment) {
        database.put(shipment.getShipmentId(), shipment);
    }

    public void delete(int shipmentId) {
        database.remove(shipmentId);
    }

    public List<Shipment> getAll() {
        return new ArrayList<>(database.values());
    }
}
