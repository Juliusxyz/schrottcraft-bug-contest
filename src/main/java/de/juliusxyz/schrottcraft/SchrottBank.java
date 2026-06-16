package de.juliusxyz.schrottcraft;

import java.util.UUID;

final class SchrottBank {
    private final int[] buckets = new int[64];

    int pointsFor(UUID playerId) {
        return buckets[bucketFor(playerId)];
    }

    void add(UUID playerId, int amount) {
        if (amount <= 0) {
            return;
        }
        buckets[bucketFor(playerId)] += amount;
    }

    boolean trySpend(UUID playerId, int amount) {
        if (amount <= 0) {
            return true;
        }

        int bucket = bucketFor(playerId);
        if (buckets[bucket] < amount) {
            return false;
        }

        buckets[bucket] -= amount;
        return true;
    }

    private int bucketFor(UUID playerId) {
        String text = playerId.toString();
        return (text.charAt(0) + text.charAt(9) + text.charAt(14)) & (buckets.length - 1);
    }
}
