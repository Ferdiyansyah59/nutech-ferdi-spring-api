CREATE OR REPLACE FUNCTION fn_user_balance()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO user_balances (user_id)
    VALUES (NEW.id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

 CREATE TRIGGER trg_user_balance
 AFTER INSERT ON users
 FOR EACH ROW
 EXECUTE FUNCTION fn_user_balance();