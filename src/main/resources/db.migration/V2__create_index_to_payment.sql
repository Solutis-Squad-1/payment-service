-- Index for table payments
CREATE INDEX idx_payments_id ON payments (id);
CREATE INDEX idx_payments_order_id ON payments (order_id);
CREATE INDEX idx_payments_user_id ON payments (user_id);
CREATE INDEX idx_payments_form_payment ON payments (form_payment);
CREATE INDEX idx_payments_deleted ON payments (deleted);