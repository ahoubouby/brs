import { useEffect, useState } from "react";

const log = (isLogActived) => (message) => {
  if (isLogActived) console.log(message);
};
const useDebounce = (value, delay, isLogActived = false) => {
  const [debouncedValue, setDebouncedValue] = useState(value);
  useEffect(() => {
    const handler = setTimeout(() => {
      log(isLogActived)(`value = ${debouncedValue} will be debounced `);
      setDebouncedValue(value);
    }, delay);

    return () => {
      clearTimeout(handler);
    };
  }, [value, delay]);
  return [debouncedValue];
};

export default useDebounce;
