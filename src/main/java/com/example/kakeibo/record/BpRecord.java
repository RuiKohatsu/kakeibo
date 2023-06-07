package com.example.kakeibo.record;

public record BpRecord(Integer id, String date, String outIn, String category, Integer amount, String explain) {
}