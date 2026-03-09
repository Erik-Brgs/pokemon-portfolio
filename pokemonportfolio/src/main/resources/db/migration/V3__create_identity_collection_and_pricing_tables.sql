CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    display_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE collections (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_collection_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE collection_items (
    id UUID PRIMARY KEY,
    collection_id UUID NOT NULL,
    card_id UUID NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    condition VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_item_collection FOREIGN KEY (collection_id) REFERENCES collections(id),
    CONSTRAINT fk_item_card FOREIGN KEY (card_id) REFERENCES cards(id)
);

CREATE TABLE price_snapshots (
    id UUID PRIMARY KEY,
    card_id UUID NOT NULL,
    price NUMERIC(19,4) NOT NULL CHECK (price >= 0),
    source VARCHAR(100) NOT NULL,
    captured_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_price_card FOREIGN KEY (card_id) REFERENCES cards(id)
);