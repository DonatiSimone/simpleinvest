package it.simpleinvest.model;

// Autore: Donati Simone

// Enum che definisce profili di andamento preconfigurati (crescita, perdita, stabile, volatile).
// Viene usato per simulare l’andamento dei portafogli demo attraverso variazioni percentuali del capitale.

public enum AndamentoPredefinito {

    CRESCITA(new double[] {
        0.00, 0.02, 0.03, 0.01, 0.02, 0.015
    }),

    PERDITA(new double[] {
        0.00, -0.01, -0.02, -0.015, -0.01
    }),

    VOLATILE(new double[] {
        0.00, 0.03, -0.025, 0.04, -0.02, 0.015
    }),

    STABILE(new double[] {
        0.00, 0.002, -0.001, 0.001, 0.0
    });

    private final double[] variazioni;

    AndamentoPredefinito(double[] variazioni) {
        this.variazioni = variazioni;
    }

    public double[] getVariazioni() {
        return variazioni;
    }
}
