package services;

import entity.Paket;
import java.util.*;

public class ManajemenAntrianService {
    protected final Queue<Paket> prioritasQueue = new LinkedList<>();
    protected final Queue<Paket> regulerQueue = new LinkedList<>();
    protected final List<Paket> riwayat = new ArrayList<>();

    public void tambahPaket(Paket paket) {
        if (paket.isPrioritas()) {
            prioritasQueue.add(paket);
            System.out.println("✅ Paket prioritas ditambahkan: " + paket);
        } else {
            regulerQueue.add(paket);
            System.out.println("✅ Paket reguler ditambahkan: " + paket);
        }
    }

    public boolean batalkanPaket(int id) {
        for (Paket p : prioritasQueue) {
            if (p.getId() == id) {
                prioritasQueue.remove(p);
                p.setStatus(Paket.Status.DIBATALKAN);
                riwayat.add(p);
                System.out.println("❌ Dibatalkan dari prioritas: " + p);
                return true;
            }
        }

        for (Paket r : regulerQueue) {
            if (r.getId() == id) {
                regulerQueue.remove(r);
                r.setStatus(Paket.Status.DIBATALKAN);
                riwayat.add(r);
                System.out.println("❌ Dibatalkan dari reguler: " + r);
                return true;
            }
        }

        System.out.println("⚠️ ID tidak ditemukan.");
        return false;
    }

    public void cariPaketById(int id) {
        for (Paket p : prioritasQueue)
            if (p.getId() == id) { System.out.println("📦 Ditemukan di antrian prioritas: " + p); return; }

        for (Paket r : regulerQueue)
            if (r.getId() == id) { System.out.println("📦 Ditemukan di antrian reguler: " + r); return; }

        for (Paket h : riwayat)
            if (h.getId() == id) { System.out.println("📦 Ditemukan di riwayat: " + h); return; }

        System.out.println("🔍 Paket tidak ditemukan.");
    }

    public void tampilkanAntrian() {
        System.out.println("\n📋 Antrian Prioritas:");
        if (prioritasQueue.isEmpty()) System.out.println(" - Kosong");
        for (Paket p : prioritasQueue) System.out.println(p);

        System.out.println("📋 Antrian Reguler:");
        if (regulerQueue.isEmpty()) System.out.println(" - Kosong");
        for (Paket r : regulerQueue) System.out.println(r);
    }

    public List<Paket> getRiwayat() {
        return riwayat;
    }

    public Queue<Paket> getPrioritasQueue() {
        return prioritasQueue;
    }

    public Queue<Paket> getRegulerQueue() {
        return regulerQueue;
    }
}
