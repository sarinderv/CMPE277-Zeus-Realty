CREATE TABLE listing (
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL UNIQUE,
    category character varying(255) NOT NULL,
    x_coordinate numeric NOT NULL,
    y_coordinate numeric NOT NULL,
    price numeric,
    total_area numeric,
    living_area numeric,
    description character varying(255),
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO listing (id, name, category, x_coordinate, y_coordinate, price, total_area, living_area, description) VALUES 
(1, 'Manhattan', 'Apartment', -73.97, 40.77, 123, 456, 345, 'nice loft for rent');
INSERT INTO listing (id, name, category, x_coordinate, y_coordinate, price, total_area, living_area, description) VALUES 
(2, 'Central Park', 'Parks', -73.97, 40.77, 123, 456, 345, 'single bedroom w/ park view');
