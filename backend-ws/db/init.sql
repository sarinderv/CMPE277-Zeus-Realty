CREATE TABLE listing (
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL UNIQUE,
    category character varying(255) NOT NULL,
    x_coordinate numeric NOT NULL,
    y_coordinate numeric NOT NULL,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO listing (id, name, category, x_coordinate, y_coordinate) VALUES 
(1, 'Central Park', 'Parks', -73.97, 40.77);
