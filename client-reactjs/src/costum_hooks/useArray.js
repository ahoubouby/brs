export { useCallback, useState } from "react";

const useArray = (initValue) => {
  const [value, setValue] = useState(initValue);
  const add = useCallback(
    (newValue) => setValue((prev) => [...prev, newValue]),
    []
  );

  const clear = useCallback(() => setValue(() => []), []);

  const removeById = useCallback((id) =>
    setValue((prev) => prev.filter((el) => el && el.id !== id), [])
  );

  const removeIndex = useCallback(
    (index) => setValue((arr) => arr.filter((v, i) => i !== index)),
    []
  );

  return { value, add, clear, removeById, removeIndex };
};

export default useArray;
