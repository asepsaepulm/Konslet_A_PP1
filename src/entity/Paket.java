package entity;

public class Paket {
    public enum Status {
        MENUNGGU, DIKIRIM, DIBATALKAN
    }

    private static int counter = 1;

    private final int id;
    private final String alamatTujuan;
    private final double berat;
    private Status status;

    public Paket(String alamatTujuan, double berat) {
        this.id = counter++;
        this.alamatTujuan = alamatTujuan;
        this.berat = berat;
        this.status = Status.MENUNGGU;
    }

    public int getId() { return id; }
    public String getAlamatTujuan() { return alamatTujuan; }
    public double getBerat() { return berat; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public boolean isPrioritas() {
        return berat > 10.0;
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s | %.2f kg | %s", id, alamatTujuan, berat, status);
    }
}
