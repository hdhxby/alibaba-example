package io.github.example.example.web.rest.impl;

public class P {
    private String id;
    private C c;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    class C {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
