import t from '../utils/call';
export default function o() {
  return {
    validate(o) {
      const l = t(o.options.callback, [o]);
      return 'boolean' === typeof l ? { valid: l } : l;
    },
  };
}
