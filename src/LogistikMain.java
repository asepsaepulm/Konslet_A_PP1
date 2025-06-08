import entity.Paket;
import services.*;
import util.InputUtil;

public class LogistikMain {
    public static void main(String[] args) {
        // Inisialisasi layanan
        ManajemenAntrianService manajemen = new ManajemenAntrianService();
        PengirimanService pengiriman = new PengirimanService(manajemen);

        // Loop menu
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Tambah Paket");
            System.out.println("2. Kirim Paket");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Batalkan Paket");
            System.out.println("5. Cari Paket berdasarkan ID");
            System.out.println("6. Tampilkan Riwayat");
            System.out.println("7. Laporan Ringkas");
            System.out.println("0. Keluar");

            int menu = InputUtil.inputInt("Pilih menu: ");

            switch (menu) {
                case 1 -> {
                    String alamat = InputUtil.inputString("Alamat Tujuan: ");
                    double berat = InputUtil.inputDouble("Berat Paket (kg): ");
                    Paket paket = new Paket(alamat, berat);
                    manajemen.tambahPaket(paket);
                }
                case 2 -> pengiriman.kirimPaket();
                case 3 -> manajemen.tampilkanAntrian();
                case 4 -> {
                    int id = InputUtil.inputInt("Masukkan ID Paket yang ingin dibatalkan: ");
                    manajemen.batalkanPaket(id);
                }
                case 5 -> {
                    int id = InputUtil.inputInt("Masukkan ID yang ingin dicari: ");
                    manajemen.cariPaketById(id);
                }
                case 6 -> pengiriman.tampilkanRiwayat();
                case 7 -> pengiriman.laporanRingkas();
                case 0 -> {
                    System.out.println("👋 Terima kasih, program selesai.");
                    return;
                }
                default -> System.out.println("❗ Menu tidak valid. Silakan pilih lagi.");
            }
        }
    }
}
