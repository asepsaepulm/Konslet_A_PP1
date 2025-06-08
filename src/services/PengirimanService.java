package services;

import entity.Paket;
import java.util.*;

public class PengirimanService {
    private final ManajemenAntrianService antrianService;
    private int prioritasCounter = 0;

    public PengirimanService(ManajemenAntrianService antrianService) {
        this.antrianService = antrianService;
    }

    public void kirimPaket() {
        Queue<Paket> prioritasQueue = antrianService.getPrioritasQueue();
        Queue<Paket> regulerQueue = antrianService.getRegulerQueue();
        List<Paket> riwayat = antrianService.getRiwayat();

        if (!prioritasQueue.isEmpty() && (prioritasCounter < 2 || regulerQueue.isEmpty())) {
            Paket p = prioritasQueue.poll();
            p.setStatus(Paket.Status.DIKIRIM);
            prioritasCounter++;
            riwayat.add(p);
            System.out.println("🚚 Mengirim prioritas: " + p);
        } else if (!regulerQueue.isEmpty()) {
            Paket r = regulerQueue.poll();
            r.setStatus(Paket.Status.DIKIRIM);
            prioritasCounter = 0;
            riwayat.add(r);
            System.out.println("🚚 Mengirim reguler: " + r);
        } else {
            System.out.println("📦 Tidak ada paket yang bisa dikirim.");
        }
    }

    public void tampilkanRiwayat() {
        List<Paket> riwayat = antrianService.getRiwayat();
        System.out.println("\n🕘 Riwayat Pengiriman & Pembatalan:");
        if (riwayat.isEmpty()) {
            System.out.println(" - Belum ada data.");
        } else {
            for (Paket p : riwayat) System.out.println(p);
        }
    }

    public void laporanRingkas() {
        List<Paket> riwayat = antrianService.getRiwayat();
        int dikirim = 0, dibatalkan = 0;

        for (Paket p : riwayat) {
            if (p.getStatus() == Paket.Status.DIKIRIM) dikirim++;
            else if (p.getStatus() == Paket.Status.DIBATALKAN) dibatalkan++;
        }

        System.out.println("\n📊 Laporan Ringkas:");
        System.out.println(" - Total Dikirim     : " + dikirim);
        System.out.println(" - Total Dibatalkan  : " + dibatalkan);
        System.out.println(" - Antrian Prioritas : " + antrianService.getPrioritasQueue().size());
        System.out.println(" - Antrian Reguler   : " + antrianService.getRegulerQueue().size());
    }
}
